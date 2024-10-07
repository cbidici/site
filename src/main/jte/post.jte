@import com.cbidici.site.view.data.Page
@import com.cbidici.site.view.data.PostResponse
@param Page page
@param PostResponse post

@template.base(
page = page,
headElements = @`
  <link href="/css/post.css" rel="stylesheet">
  <title>${post.title()}</title>
  <meta name="description" content="${post.description()}">
`,
content = @`
  <div>
    <section class="mb-3">
      <a class="link-primary" href="/posts">Back to Posts</a>
      @if(page.hasRole("ADMIN"))
        <span>
          | <a class="link-primary" href="/posts/${post.id()}/update">Edit</a>
          @if(post.status().equals("CREATED") || post.status().equals("WITHDRAWN"))
            | <a class="link-primary" href="/posts/${post.id()}/publish">Publish</a>
          @elseif(post.status().equals("PUBLISHED"))
            | <a class="link-primary" href="/posts/${post.id()}/withdraw">Withdraw</a>
          @endif
          | <a class="link-primary" href="/posts/${post.id()}/delete" onclick="return confirm('Are you sure you want to delete this post?')">Delete</a>
        </span>
      @endif
    </section>
    <section class="mb-3">
      <h1>${post.title()}</h1>
      <div class="post-content">$unsafe{post.content()}</div>
      <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/11.5.0/styles/default.min.css">
      <script src="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/11.5.0/highlight.min.js"></script>
      <script>hljs.highlightAll();</script>
    </section>
  </div>
`)
