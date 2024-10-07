@import com.cbidici.site.view.data.FileResponse
@import com.cbidici.site.view.data.Page
@import java.util.List
@param Page page
@param List<FileResponse> files

@template.base(
page = page,
headElements = @`
    <title>Coşkun Bıdıcı's Sandbox - Storage</title>
`,
content = @`
  <div>
    <section class="mb-3">
      <div class="shadow-sm mb-3 p-3 bg-white">
        <div class="card-body">
          <div class="row">
            <div class="col">
              <form method="POST" enctype="multipart/form-data" action="/storage">
                <input type="hidden" name="_csrf" value="${page.getCsrfToken()}">
                <div class="row mb-2">
                  <div class="input-group col-sm">
                    <input type="file" name="file" id="file" class="form-control" />
                    <button class="btn btn-primary" type="submit">Upload</button>
                  </div>
                </div>
              </form>
            </div>
          </div>
        </div>
      </div>
    </section>
    <section class="mb-3">
      <div class="shadow-sm mb-3 p-3 bg-white">
        <ul>
          @for(var file : files)
            <li>
              <a href="${file.url()}" target="_blank">${file.name()}</a>
            </li>
          @endfor
        </ul>
      </div>
    </section>
  </div>
`)