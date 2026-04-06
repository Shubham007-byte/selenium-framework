# Jenkins CI/CD Setup Guide

## Step 1: Download & Install Jenkins

### Windows Installation
1. **Download Jenkins:**
   - Go to https://www.jenkins.io/download/
   - Click "Windows installer (.msi)"

2. **Run the installer:**
   - Double-click `jenkins.msi`
   - Click "Next" through installation wizard
   - Keep default settings
   - Choose "Run Jenkins as Local System"
   - Click "Install"

3. **Jenkins starts automatically**
   - Service will run on `http://localhost:8080`

---

## Step 2: Unlock Jenkins

1. **Get Initial Admin Password:**
   ```
   C:\Program Files\Jenkins\secrets\initialAdminPassword
   ```
   - Open this file and copy the password

2. **Unlock Jenkins:**
   - Go to `http://localhost:8080` in browser
   - Paste the password
   - Click "Continue"

3. **Install Suggested Plugins:**
   - Click "Install suggested plugins"
   - Wait for installation (5-10 minutes)

4. **Create Admin User:**
   - Enter username, password, name, email
   - Click "Save and Continue"

---

## Step 3: Install Required Plugins

1. **Go to:** `Manage Jenkins` → `Manage Plugins`

2. **Search and Install:**
   - **Maven Integration Plugin**
   - **Git Plugin** (usually pre-installed)
   - **Allure Jenkins Plugin**
   
3. **Steps:**
   - Search for plugin name
   - Check the checkbox
   - Click "Install without restart"
   - Restart Jenkins after all plugins

---

## Step 4: Configure Global Tools

1. **Go to:** `Manage Jenkins` → `Global Tool Configuration`

### Configure JDK
1. Click "Add JDK"
2. Enter:
   - **Name:** `JDK21`
   - **JAVA_HOME:** 
     ```
     C:\Program Files\Java\jdk-21
     ```
     (Adjust based on your Java installation)

### Configure Maven
1. Click "Add Maven"
2. Enter:
   - **Name:** `Maven`
   - **MAVEN_HOME:** 
     ```
     C:\Program Files\apache-maven-3.9.11
     ```
     (Or wherever Maven is installed)

### Configure Git
1. Click "Add Git"
2. Enter:
   - **Name:** `Default`
   - **Path to Git executable:** 
     ```
     C:\Program Files\Git\bin\git.exe
     ```

3. Click "Save"

---

## Step 5: Create Jenkins Job

### Create New Job
1. Click **"New Item"** (top-left)
2. Enter **Job name:** `Selenium-Framework`
3. Select **"Freestyle project"**
4. Click **"OK"**

### Configure Source Code Management
1. **Section:** "Source Code Management"
2. Select **"Git"**
3. Enter:
   - **Repository URL:** 
     ```
     https://github.com/Shubham007-byte/selenium-framework.git
     ```
   - **Credentials:** (Leave empty for public repos)
   - **Branch:** `*/main`

### Configure Build Step
1. **Section:** "Build"
2. Click **"Add build step"** → **"Invoke top-level Maven targets"**
3. Enter:
   - **Maven Version:** `Maven`
   - **Goals:** 
     ```
     clean test
     ```

### Configure Post-Build Actions (Allure Report)
1. **Section:** "Post-build Actions"
2. Click **"Add post-build action"** → **"Allure Report"**
3. Enter:
   - **Results Directory:** 
     ```
     target/allure-results
     ```

4. Click **"Save"**

---

## Step 6: Run the Build

1. Go to your job: `Selenium-Framework`
2. Click **"Build Now"**
3. Monitor the build progress in **"Build History"**

### View Build Output
1. Click the build number (e.g., `#1`)
2. Click **"Console Output"**
3. Watch tests execute in real-time

### View Allure Report
1. After build completes successfully
2. Scroll down to find **"Allure Report"** link
3. Click to view detailed test report with screenshots

---

## Step 7: Enable Automatic Triggers

### Option A: Poll SCM (Checks Every 2 Hours)
1. Go to job configuration
2. **Section:** "Build Triggers"
3. Check **"Poll SCM"**
4. Enter in schedule:
   ```
   H/2 * * * *
   ```
   (Checks every 2 hours)
5. Click "Save"

### Option B: GitHub Webhook (Real-time - Better) ⭐

#### On Jenkins:
1. Go to job configuration
2. **Section:** "Build Triggers"
3. Check **"GitHub hook trigger for GITScm polling"**
4. Click "Save"

#### On GitHub:
1. Go to your repo: `https://github.com/Shubham007-byte/selenium-framework`
2. Click **"Settings"**
3. Click **"Webhooks"** (left sidebar)
4. Click **"Add webhook"**
5. Enter:
   - **Payload URL:** 
     ```
     http://YOUR-MACHINE-IP:8080/github-webhook/
     ```
     (Replace `YOUR-MACHINE-IP` with your computer's IP)
   - **Content type:** `application/json`
   - **Events:** Select "Just the push event"
6. Click **"Add webhook"**

---

## Testing the Pipeline

### Method 1: Manual Trigger
1. Go to Jenkins job
2. Click **"Build Now"**

### Method 2: Git Push (with webhook)
```powershell
cd c:\selenium-framework
echo "# Updated" >> README.md
git add .
git commit -m "Test Jenkins webhook"
git push
```
Jenkins will automatically trigger the build within 5 seconds!

---

## Expected Outcomes

✅ **Console Output:**
- Code cloned from GitHub
- Maven compilation successful
- TestNG tests executed
- Allure results generated

✅ **Allure Report Shows:**
- Test name and status (PASSED/FAILED)
- Test execution time
- Screenshots on failure
- Detailed logs

---

## Troubleshooting

### Build Fails - "Maven not found"
- Check `Manage Jenkins → Global Tool Configuration`
- Verify Maven path is correct

### Tests Don't Run - "Chrome driver not found"
- WebDriverManager auto-downloads driver
- Make sure chrome browser is installed

### Allure Report Missing
- Verify `target/allure-results` path in post-build action
- Run `mvn clean test` locally first

### GitHub webhook not triggering
- Verify webhook is added in GitHub Settings
- Check firewall allows inbound connections on port 8080

---

## Next Steps

1. ✅ Test build manually
2. ✅ Configure GitHub webhook
3. ✅ Make a Git commit and verify auto-trigger
4. ✅ View Allure report in Jenkins
5. ✅ Add more test cases and watch CI/CD pipeline work!

