# GitHub Pages Setup Guide

This repository is configured for GitHub Pages using Docsify. Follow these steps to deploy your documentation site.

## 🚀 Quick Setup

### Option 1: Using GitHub Actions (Recommended)

1. **Push your code to GitHub:**
   ```bash
   git add .
   git commit -m "Setup GitHub Pages with Docsify"
   git push origin main
   ```

2. **Enable GitHub Pages:**
   - Go to your repository on GitHub
   - Click **Settings** → **Pages**
   - Under **Source**, select:
     - **Source**: `GitHub Actions`
   - Click **Save**

3. **Wait for deployment:**
   - Go to **Actions** tab
   - Wait for the "Deploy to GitHub Pages" workflow to complete
   - Your site will be available at: `https://YOUR_USERNAME.github.io/YOUR_REPO_NAME/`

### Option 2: Manual Setup (Alternative)

1. **Enable GitHub Pages:**
   - Go to **Settings** → **Pages**
   - Under **Source**, select:
     - **Branch**: `main`
     - **Folder**: `/ (root)`
   - Click **Save**

2. **Your site will be available at:**
   `https://YOUR_USERNAME.github.io/YOUR_REPO_NAME/`

## 📝 Update Repository URL

Before deploying, update the repository URL in `index.html`:

```html
repo: 'https://github.com/Tanvisharma31/Comprehensive-Java-Developer-Repository',
```

Replace `YOUR_USERNAME` and `YOUR_REPO` with your actual GitHub username and repository name.

## ✅ Files Included

- ✅ `index.html` - Docsify configuration
- ✅ `_sidebar.md` - Navigation sidebar
- ✅ `.nojekyll` - Disables Jekyll processing
- ✅ `.github/workflows/pages.yml` - GitHub Actions workflow

## 🎨 Customization

### Change Theme

Edit `index.html` and modify the CSS link:
```html
<link rel="stylesheet" href="//cdn.jsdelivr.net/npm/docsify@4/lib/themes/vue.css">
```

Available themes:
- `vue.css` (default)
- `buble.css`
- `dark.css`
- `pure.css`

### Add Plugins

Add more Docsify plugins in `index.html`:
```html
<!-- Example: Zoom Image Plugin -->
<script src="//cdn.jsdelivr.net/npm/docsify/lib/plugins/zoom-image.min.js"></script>
```

## 🔍 Features Enabled

- ✅ Search functionality
- ✅ Copy code button
- ✅ Java syntax highlighting
- ✅ Sidebar navigation
- ✅ Auto-generated table of contents

## 📚 Documentation Structure

Your documentation is organized as:
- `ULTIMATE_LEARNING_GUIDE.md` - Homepage
- `_sidebar.md` - Navigation menu
- All markdown files in subdirectories are automatically indexed

## 🐛 Troubleshooting

### Site not loading?
- Check that `.nojekyll` file exists in root
- Verify `index.html` is in root directory
- Check GitHub Actions workflow status

### Sidebar not showing?
- Ensure `_sidebar.md` exists in root
- Check that `loadSidebar: true` is set in `index.html`

### Search not working?
- Verify search plugin is loaded in `index.html`
- Check that markdown files are accessible

## 🎉 Success!

Once deployed, your documentation site will be live and accessible to everyone!

---

**Need help?** Check the [Docsify documentation](https://docsify.js.org/)
