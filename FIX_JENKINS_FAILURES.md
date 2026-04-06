# Fix Jenkins Build Failures - Quick Guide

## 🔴 Current Failures

1. **Maven not found error**
2. **Allure commandline not installed**

---

## ✅ Fix 1: Configure Maven in Jenkins (CRITICAL)

1. **Open Jenkins:** `http://localhost:8080`

2. **Go to:** `Manage Jenkins` → `Global Tool Configuration`

3. **Find the "Maven" section** (scroll down)

4. **Delete any incorrect Maven entries** by clicking "Delete"

5. **Click "Add Maven"** button

6. **Fill in EXACTLY:**
   ```
   Name: Maven
   MAVEN_HOME: C:\Program Files\Maven\apache-maven-3.9.14
   ```
   - **IMPORTANT:** Uncheck "Install automatically" 
   - We want to use your local installed Maven

7. **Click "Save"** button at bottom

---

## ✅ Fix 2: Configure Allure in Jenkins

1. **In same "Global Tool Configuration" page**

2. **Find "Allure Commandline" section** (scroll down)

3. **Click "Add Allure Commandline"** button

4. **Fill in:**
   ```
   Name: Allure
   ```
   - **CHECK:** "Install automatically" checkbox ✅
   - **Version:** Select latest (e.g., 2.24.1 or similar)

5. **Click "Save"** button

---

## ✅ Fix 3: Update Job Configuration

1. **Open your job:** `Selenium-Framework`

2. **Click "Configure"**

3. **Scroll to "Build" section**

4. **In Build Step - "Invoke top-level Maven targets":**
   - **Maven Version:** Click dropdown → Select **"Maven"** (the one you just configured)
   - Keep **Goals:** `clean test`

5. **Scroll to "Post-build Actions"**

6. **In "Allure Report" section:**
   - **Results Directory:** `target/allure-results`
   - **Report path pattern:** (leave empty)
   - **Allure Version:** Select **"Allure"** from dropdown (the one you just configured)
   - Check: "Keep build logs"

7. **Click "Save"**

---

## 🚀 Test the Fix

1. **Go back to job:** `Selenium-Framework`

2. **Click "Build Now"**

3. **Monitor build:**
   - Click on build #3 (or next build number)
   - Click "Console Output"
   - Watch for:
     ```
     [INFO] Tests run: 1, Failures: 0, Errors: 0
     [INFO] BUILD SUCCESS
     ```

---

## 📋 Verification Checklist

Before clicking "Build Now" again, verify:

- [ ] Maven path is set to: `C:\Program Files\Maven\apache-maven-3.9.14`
- [ ] Build step has Maven Version selected in dropdown
- [ ] Allure Commandline is configured with "Install automatically" checked
- [ ] Allure Report post-build action has Results Directory: `target/allure-results`
- [ ] Allure Version dropdown has selection

---

## If Still Fails

Run this command locally to verify your setup:
```powershell
cd c:\selenium-framework
mvn clean test
```

This will show if Maven works locally. If it does, the issue is Jenkins configuration.

---

## ⚠️ Common Mistakes

❌ Maven Version field left blank → Configure in Global Tool Configuration first
❌ Wrong Maven path → Must be: `C:\Program Files\Maven\apache-maven-3.9.14`
❌ Allure not installed → Check "Install automatically" option
❌ Post-build action has wrong path → Must be: `target/allure-results`

---

**Complete all steps above, then click "Build Now" again!** 🚀
