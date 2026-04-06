# Create Jenkins Job - Step by Step Guide

## Prerequisites
✅ Jenkins running at `http://localhost:8080`
✅ Logged in to Jenkins
✅ Required plugins installed (Maven, Git, Allure)
✅ Global Tools configured with JDK, Maven, Git paths

---

## Step 1: Create New Job

1. **Click "New Item"** (top-left corner of Jenkins dashboard)

2. **Enter Job Name:**
   ```
   Selenium-Framework
   ```

3. **Select Job Type:**
   - Click **"Freestyle project"**
   - (Not Pipeline, not Multibranch)

4. **Click "OK"** button

---

## Step 2: Configure Source Code Management

Your browser will now show the job configuration page.

### Scroll to: "Source Code Management" Section

1. **Select "Git"** (radio button)

2. **Fill in Repository URL:**
   ```
   https://github.com/Shubham007-byte/selenium-framework.git
   ```
   - Copy & paste the URL above in the "Repository URL" field

3. **Credentials:**
   - Leave EMPTY (since your GitHub repo is public)

4. **Branch Specifier:**
   ```
   */main
   ```
   - Clear existing text and enter `*/main`

---

## Step 3: Configure Build Triggers (Optional - For Now Skip)

### Scroll to: "Build Triggers" Section

For now, we'll skip this. You can enable auto-trigger later after testing manual build.

---

## Step 4: Configure Build Steps

### Scroll to: "Build" Section

1. **Click "Add build step"** (dropdown button)

2. **Select "Invoke top-level Maven targets"**

3. **Fill in:**
   - **Maven Version:** Select `Maven` from dropdown
   - **Goals:** 
     ```
     clean test
     ```

---

## Step 5: Configure Post-Build Actions (Allure Report)

### Scroll to: "Post-build Actions" Section

1. **Click "Add post-build action"** (dropdown button)

2. **Select "Allure Report"**

3. **Fill in:**
   - **Results Directory:**
     ```
     target/allure-results
     ```

4. Leave other options as default

---

## Step 6: Save Job Configuration

1. **Scroll to bottom**

2. **Click "Save" button**

✅ **Your Jenkins job is now created!**

---

## Step 7: Test the Job

### Run Build Manually

1. **Go to your job:** Click "Selenium-Framework" in left sidebar

2. **Click "Build Now"** button

3. **Monitor Build Progress:**
   - In "Build History" (left), click on "#1" (first build)
   - Click "Console Output" to watch real-time execution

---

## Expected Console Output

```
[INFO] Scanning for projects...
[INFO] Building selenium-framework 1.0-SNAPSHOT
[INFO] --- clean:3.2.0:clean (default-clean) @ selenium-framework ---
[INFO] Deleting C:\selenium-framework\target
[INFO] --- compiler:3.13.0:compile (default-compile) @ selenium-framework ---
[INFO] Compiling 6 source files...
[INFO] --- compiler:3.13.0:testCompile (default-testCompile) @ selenium-framework ---
[INFO] Compiling 1 source file...
[INFO] --- surefire:3.2.5:test (default-test) @ selenium-framework ---
[INFO] Tests run: 1, Failures: 0, Errors: 0
[INFO] 
[INFO] BUILD SUCCESS
```

✅ If you see "BUILD SUCCESS" → Job is working! 🎉

---

## Step 8: View Allure Report

1. **After successful build**, go back to job main page

2. **Look for "Allure Report"** link (appears after first successful build)

3. **Click "Allure Report"** to see:
   - Test status (PASSED/FAILED)
   - Test execution time
   - Screenshots
   - Detailed logs

---

## Configuration Summary

| Setting | Value |
|---------|-------|
| **Job Name** | Selenium-Framework |
| **Job Type** | Freestyle project |
| **Git Repository** | https://github.com/Shubham007-byte/selenium-framework.git |
| **Branch** | */main |
| **Build Command** | clean test |
| **Maven Version** | Maven |
| **Allure Results** | target/allure-results |

---

## Troubleshooting

### Build Fails with "Maven not found"
- Go to `Manage Jenkins → Global Tool Configuration`
- Verify Maven path is correct: `C:\Program Files\Maven\apache-maven-3.9.14`

### Build Fails with "Java not found"
- Go to `Manage Jenkins → Global Tool Configuration`
- Verify JDK path is correct: `C:\jdk-21\jdk-21.0.8`

### No Allure Report after build
- Check if `target/allure-results` path in post-build action is correct
- Manually run `mvn clean test` in your project folder to verify it works

### Git clone fails
- Verify internet connection
- Try cloning manually: 
  ```
  git clone https://github.com/Shubham007-byte/selenium-framework.git
  ```

---

## Next Steps After Successful Build

1. ✅ Test build completes successfully
2. ✅ View Allure report in Jenkins
3. ✅ Enable GitHub webhook for auto-trigger
4. ✅ Make a Git push and watch Jenkins auto-trigger build
5. ✅ Add more test cases and expand your test suite

---

## Enable Automatic Triggers (After Testing)

### Option A: Poll SCM (Every 2 Hours)
1. Go to job configuration
2. Check "Poll SCM"
3. Enter: `H/2 * * * *`
4. Save

### Option B: GitHub Webhook (Real-time - Better)
1. Go to job configuration
2. Check "GitHub hook trigger for GITScm polling"
3. Save
4. Go to GitHub repo → Settings → Webhooks → Add webhook
5. Payload URL: `http://YOUR-IP:8080/github-webhook/`
6. Save

---

**You're all set! Proceed with creating the job now.** 🚀
