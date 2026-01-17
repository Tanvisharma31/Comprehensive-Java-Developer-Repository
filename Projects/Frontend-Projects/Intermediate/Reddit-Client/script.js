document.getElementById('load-btn').addEventListener('click', loadPosts);
document.getElementById('subreddit-input').addEventListener('keypress', function(e) {
    if (e.key === 'Enter') {
        loadPosts();
    }
});

async function loadPosts() {
    const subreddit = document.getElementById('subreddit-input').value.trim();
    const loading = document.getElementById('loading');
    const postsContainer = document.getElementById('posts-container');
    const error = document.getElementById('error');
    
    if (!subreddit) {
        error.textContent = 'Please enter a subreddit name';
        error.hidden = false;
        return;
    }
    
    postsContainer.innerHTML = '';
    error.hidden = true;
    loading.hidden = false;
    
    try {
        const response = await fetch(`https://www.reddit.com/r/${subreddit}.json?limit=10`);
        
        if (!response.ok) {
            throw new Error('Failed to fetch posts');
        }
        
        const data = await response.json();
        
        if (!data.data || !data.data.children || data.data.children.length === 0) {
            throw new Error('No posts found or subreddit does not exist');
        }
        
        data.data.children.forEach(post => {
            const postData = post.data;
            const postCard = createPostCard(postData);
            postsContainer.appendChild(postCard);
        });
    } catch (err) {
        error.textContent = `Error: ${err.message}`;
        error.hidden = false;
    } finally {
        loading.hidden = true;
    }
}

function createPostCard(postData) {
    const card = document.createElement('div');
    card.className = 'post-card';
    
    const header = document.createElement('div');
    header.className = 'post-header';
    header.innerHTML = `<span>r/${postData.subreddit}</span> • <span>u/${postData.author}</span>`;
    
    const title = document.createElement('h2');
    title.className = 'post-title';
    const titleLink = document.createElement('a');
    titleLink.href = `https://reddit.com${postData.permalink}`;
    titleLink.target = '_blank';
    titleLink.textContent = postData.title;
    title.appendChild(titleLink);
    
    const content = document.createElement('div');
    content.className = 'post-content';
    if (postData.selftext) {
        content.textContent = postData.selftext.substring(0, 200) + (postData.selftext.length > 200 ? '...' : '');
    } else if (postData.url) {
        content.innerHTML = `<a href="${postData.url}" target="_blank">${postData.url}</a>`;
    }
    
    const footer = document.createElement('div');
    footer.className = 'post-footer';
    footer.innerHTML = `
        <span>⬆️ ${postData.ups}</span>
        <span>💬 ${postData.num_comments} comments</span>
        <span>🕐 ${formatTime(postData.created_utc)}</span>
    `;
    
    card.appendChild(header);
    card.appendChild(title);
    if (content.textContent || content.innerHTML) {
        card.appendChild(content);
    }
    card.appendChild(footer);
    
    return card;
}

function formatTime(timestamp) {
    const date = new Date(timestamp * 1000);
    const now = new Date();
    const diff = now - date;
    const hours = Math.floor(diff / (1000 * 60 * 60));
    
    if (hours < 1) return 'just now';
    if (hours < 24) return `${hours}h ago`;
    return `${Math.floor(hours / 24)}d ago`;
}

// Load default subreddit on page load
loadPosts();
