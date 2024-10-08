@import com.cbidici.site.view.data.Page
@import com.cbidici.site.view.data.PostUpdateView
@param Page page
@param PostUpdateView post

@template.base(
page = page,
headElements = @`
  <title>Update Post</title>
`,
content = @`
  <div>
    <section class="mb-3">
      <a class="link-primary" href="/posts">Back to Posts</a>
    </section>
    <section class="mb-3">
      <h2>Update Post</h2>
      <form action="/posts/${post.id()}/update" method="post">
        <input type="hidden" name="_csrf" value="${page.getCsrfToken()}">
        <div class="row mb-2">
          <label for="title" class="col-sm-2 col-form-label">Title:</label>
          <div class="col-sm-10">
            <input type="text" name="title" value="${post.title()}" id="title" class="form-control" maxlength="60">
          </div>
        </div>
        <div class="row mb-2">
          <label for="description" class="col-sm-2 col-form-label">Description:</label>
          <div class="col-sm-10">
            <textarea rows="2" name="description" id="description" class="form-control">${post.description()}</textarea>
          </div>
        </div>
        <div class="row mb-2">
          <label for="content" class="col-sm-2 col-form-label">Content:</label>
          <div class="col-sm-10">
            <textarea rows="16" name="content" id="content" class="form-control">${post.content()}</textarea>
          </div>
        </div>
        <a href="${post.url()}" role="button" class="btn btn-primary">Cancel</a>
        <button class="btn btn-primary" type="submit">Save</button>
      </form>
    </section>
  </div>
`)
