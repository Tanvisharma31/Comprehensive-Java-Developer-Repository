document.getElementById('find-repo-btn').addEventListener('click', async function() {
    const loading = document.getElementById('loading');
    const repoCard = document.getElementById('repo-card');
    const error = document.getElementById('error');
    
    // Hide previous results
    repoCard.hidden = true;
    error.hidden = true;
    loading.hidden = false;
    
    try {
        // Generate random search query
        const randomWords = ['javascript', 'python', 'react', 'node', 'java', 'html', 'css', 'api', 'web', 'app'];
        const randomWord = randomWords[Math.floor(Math.random() * randomWords.length)];
        
        // Search for repositories
        const searchResponse = await fetch(`https://api.github.com/search/repositories?q=${randomWord}&sort=stars&order=desc&per_page=100`);
        
        if (!searchResponse.ok) {
            throw new Error('Failed to fetch repositories');
        }
        
        const searchData = await searchResponse.json();
        
        if (searchData.items.length === 0) {
            throw new Error('No repositories found');
        }
        
        // Pick a random repository
        const randomRepo = searchData.items[Math.floor(Math.random() * searchData.items.length)];
        
        // Display repository
        document.getElementById('repo-name').textContent = randomRepo.full_name;
        document.getElementById('repo-link').href = randomRepo.html_url;
        document.getElementById('repo-description').textContent = randomRepo.description || 'No description available';
        document.getElementById('repo-stars').textContent = randomRepo.stargazers_count.toLocaleString();
        document.getElementById('repo-forks').textContent = randomRepo.forks_count.toLocaleString();
        document.getElementById('repo-watchers').textContent = randomRepo.watchers_count.toLocaleString();
        document.getElementById('repo-language').textContent = randomRepo.language || 'N/A';
        
        const updatedDate = new Date(randomRepo.updated_at);
        document.getElementById('repo-updated').textContent = `Updated ${updatedDate.toLocaleDateString()}`;
        
        repoCard.hidden = false;
    } catch (err) {
        error.textContent = `Error: ${err.message}`;
        error.hidden = false;
    } finally {
        loading.hidden = true;
    }
});
