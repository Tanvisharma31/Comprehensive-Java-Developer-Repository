# Code Review Best Practices

## For the Reviewer
1.  **Understand the Context**: Read the description and ticket before looking at code.
2.  **Be Constructive**: "Why is this used here?" instead of "This is wrong."
3.  **Focus on Important Issues**: Architecture, bugs, security, maintainability. Don't nitpick formatting (use a linter for that).
4.  **Performance**: Look for N+1 queries, unoptimized loops.
5.  **Tests**: Ensure tests are added or updated.

## For the Submitter
1.  **Small PRs**: Limit to 200-400 lines. Easier to review.
2.  **Self-Review**: Review your own code before submitting.
3.  **Description**: Explain *what* changed, *why* it changed, and *how* to test it.
4.  **No Ghosting**: Respond to comments promptly.
