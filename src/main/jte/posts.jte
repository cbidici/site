@import com.cbidici.site.view.data.Page
@import com.cbidici.site.view.data.PostCardResponse
@import java.util.List

@param Page page
@param List<PostCardResponse> posts

@template.base(
page = page,
headElements = @`
  <link href="/css/posts.css" rel="stylesheet">
  <title>Coşkun Bıdıcı's Sandbox - Posts</title>
  <meta name="description" content="Engineering Blog Posts Authored by Coşkun Bıdıcı">
`,
content = @`
  <div>
    @if(page.hasRole("ADMIN"))
      <section class="mb-3">
        <a class="link-primary" href="/posts/create">Create New Post</a>
      </section>
    @endif
    <section class="mb-3">
      @for(PostCardResponse post : posts)
        <article class="shadow-sm mb-3 p-3 bg-white">
          <a class="card-title text-decoration-none" href="${post.url()}">
            <div class="card-body">
              <h6 class="card-title">${post.title()}</h6>
              @if(post.description() != null)
                <p></p>
                <p class="card-text">${post.description()}</p>
              @endif
              <p class="card-text">
                @if(post.publishedAt()!=null)
                  <small class="text-body-secondary">Published At ${post.publishedAt().toString()}</small>
                  @if(page.hasRole("ADMIN"))
                    <small class="text-body-secondary"> - </small>
                  @endif
                @endif
                @if(page.hasRole("ADMIN"))
                  <small class="text-body-secondary">Created At ${post.createdAt().toString()}</small>
                @endif
                @if(page.hasRole("ADMIN"))
                  !{var pillClass = post.status().equals("CREATED") ? "text-bg-primary" : post.status().equals("PUBLISHED") ? "text-bg-success" : post.status().equals("WITHDRAWN") ? "text-bg-secondary" : "";}
                  <span class="float-end status-badge badge ${pillClass}">${post.status()}</span>
                @endif
              </p>
            </div>
          </a>
        </article>
      @endfor
    </section>
  </div>
`)