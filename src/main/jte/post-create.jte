@import com.cbidici.site.view.data.Page
@param Page page

@template.base(
page = page,
headElements = @`
  <title>Create Post</title>
`,
content = @`
  <div>
    <section class="mb-3">
      <a class="link-primary" href="/posts">Back to Posts</a>
    </section>
    <section class="mb-3">
      <h2>Create New Post</h2>
      <form action="/posts/create" method="post">
        <input type="hidden" name="_csrf" value="${page.getCsrfToken()}">
        <div class="row mb-2">
          <label for="title" class="col-sm-2 col-form-label">Title:</label>
          <div class="col-sm-10">
            <input type="text" name="title" id="title" class="form-control" maxlength="60">
          </div>
        </div>
        <div class="row mb-2">
          <label for="description" class="col-sm-2 col-form-label">Description:</label>
          <div class="col-sm-10">
            <textarea rows="2" name="description" id="description" class="form-control"></textarea>
          </div>
        </div>
        <div class="row mb-2">
          <label for="content" class="col-sm-2 col-form-label">Content:</label>
          <div class="col-sm-10">
            <textarea rows="16" name="content" id="content" class="form-control"></textarea>
          </div>
        </div>
        <button class="btn btn-primary" type="submit">Save</button>
      </form>
    </section>
  </div>
`);