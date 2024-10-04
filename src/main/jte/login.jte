@import com.cbidici.site.controller.data.Page
@param Page page
@param String error

@template.base(
page = page,
headElements = @`
  <link href="/css/profile.css" rel="stylesheet">
  <title>Coşkun Bıdıcı's Sandbox - Login</title>
`,
content = @`
  <div class="d-flex justify-content-center">
    <div class="mt-5" style="max-width:480px">
      <form action="/login" method="post">
        <input type="hidden" name="_csrf" value="${page.crsf().getToken()}">
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
