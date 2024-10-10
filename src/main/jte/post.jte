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
      <ul class="list-unstyled">
        <li class="d-inline"><a style="float:left;" class="btn btn-link" href="/posts">Back to Posts</a></li>
          @if(page.hasRole("ADMIN"))
            <li class="d-inline"><a style="float:left;" class="btn btn-link" href="/posts/${post.id()}/update">Edit</a></li>
            @if(post.status().equals("CREATED") || post.status().equals("WITHDRAWN"))
              <li class="d-inline">
                <form style="float:left;" action="/posts/${post.id()}/publish" method="post">
                  <input type="hidden" name="_csrf" value="${page.getCsrfToken()}">
                  <button type="submit" class="btn btn-link" onclick="return confirm('Are you sure you want to publish this post?')">Publish</button>
                </form>
              </li>
            @elseif(post.status().equals("PUBLISHED"))
              <li class="d-inline">
                <form style="float:left;" action="/posts/${post.id()}/withdraw" method="post">
                  <input type="hidden" name="_csrf" value="${page.getCsrfToken()}">
                  <button type="submit" class="btn btn-link" onclick="return confirm('Are you sure you want to withdraw this post?')">Withdraw</button>
                </form>
              </li>
            @endif
          <li class="d-inline">
            <form style="float:left;" action="/posts/${post.id()}/delete" method="post">
              <input type="hidden" name="_csrf" value="${page.getCsrfToken()}">
              <button type="submit" class="btn btn-link" onclick="return confirm('Are you sure you want to delete this post?')">Delete</button>
            </form>
          </li>
        @endif
      </ul>
      <br>
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
