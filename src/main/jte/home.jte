@import com.cbidici.site.view.data.Page
@import com.cbidici.site.view.data.PostTitleResponse
@import com.cbidici.site.quote.Quote
@import java.util.List
@param Page page
@param Quote quote
@param List<PostTitleResponse> mostRecent
@param List<PostTitleResponse> mostRead

@template.base(
page = page,
headElements = @`
  <title>Coşkun Bıdıcı's Sandbox</title>
`,
content = @`
  <div class="container">
    <div class="row">
      <div class="col-lg shadow-sm mb-3 p-3 bg-white">
        <section>
          <blockquote class="m-2 fs-5">
            <p class="mb-4 lh-sm">${quote.quote()}</p>
            <footer class="blockquote-footer">${quote.owner()}</footer>
          </blockquote>
        </section>
      </div>
    </div>
    <div class="row">
      <div class="col-lg shadow-sm mb-3 p-3 bg-white me-lg-2">
        <div class="card-body">
          <span class="card-title">Recently Added</span>
          <hr>
          <ul class="px-4">
            @for(var post : mostRecent)
              <li><a class="d-block link-dark link-underline link-underline-opacity-0 link-underline-opacity-100-hover" href="${post.url()}"><div class="mb-2 mt-2">${post.title()}</div></a></li>
            @endfor
          </ul>
        </div>
      </div>
      <div class="col-lg shadow-sm mb-3 p-3 bg-white ms-lg-2">
        <div class="card-body">
          <span class="card-title">Most Read</span>
          <hr>
          <ul class="px-4">
            @for(var post : mostRead)
              <li><a class="d-block link-dark link-underline link-underline-opacity-0 link-underline-opacity-100-hover" href="${post.url()}"><div class="mb-2 mt-2">${post.title()}</div></a></li>
            @endfor
          </ul>
        </div>
      </div>
    </div>
  </div>
`)