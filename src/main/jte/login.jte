@import com.cbidici.site.view.data.Page
@param Page page
@param String error

@template.base(
page = page,
headElements = @`
  <link href="/css/profile.css" rel="stylesheet">
  <title>Coşkun Bıdıcı's Sandbox - Login</title>
  <meta name="description" content="Self developed, hosted and authored software engineering blog by Coşkun Bıdıcı">
`,
content = @`
  <div class="d-flex justify-content-center">
    <div class="mt-5" style="max-width:480px">
      <form action="/login" method="post">
        <input type="hidden" name="_csrf" value="${page.getCsrfToken()}">
        <div class="row mb-2">
          <div class="col-sm-12">
            <input type="text" class="form-control" id="username" name="username" placeholder="Username">
          </div>
        </div>
        <div class="row mb-2">
          <div class="col-sm-12">
            <input type="password" class="form-control" id="password" name="password" placeholder="Password">
          </div>
        </div>
        @if(error != null)
          <div>
            <p style="color:red;">Invalid username or password</p>
          </div>
        @endif
        <button type="submit" class="btn btn-primary">Login</button>
      </form>
    </div>
  </div>
`)
