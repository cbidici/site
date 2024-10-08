@import gg.jte.support.ForSupport
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
        <table class="table table-hover">
          <tbody>
            @for(var file : ForSupport.of(files))
              <tr>
                <td><a id="file_link_${file.getIndex()}" href="${file.get().url()}" target="_blank">${file.get().name()}</a></td>
                <td class="text-end">
                  <form action="/storage/${file.get().name()}/delete" method="post">
                    <input type="hidden" name="_csrf" value="${page.getCsrfToken()}">
                    <button type="submit" class="btn btn-sm btn-link" onclick="return confirm('Are you sure you want to delete this file?')">
                      <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-trash" viewBox="0 0 16 16">
                        <path d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5m2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5m3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0z"/>
                        <path d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1zM4.118 4 4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4zM2.5 3h11V2h-11z"/>
                      </svg>
                    </button>
                    <a href="JavaScript:;" class="btn btn-sm btn-link" onclick="navigator.clipboard.writeText(document.getElementById('file_link_${file.getIndex()}').href);">
                      <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-clipboard" viewBox="0 0 16 16">
                        <path d="M4 1.5H3a2 2 0 0 0-2 2V14a2 2 0 0 0 2 2h10a2 2 0 0 0 2-2V3.5a2 2 0 0 0-2-2h-1v1h1a1 1 0 0 1 1 1V14a1 1 0 0 1-1 1H3a1 1 0 0 1-1-1V3.5a1 1 0 0 1 1-1h1z"/>
                        <path d="M9.5 1a.5.5 0 0 1 .5.5v1a.5.5 0 0 1-.5.5h-3a.5.5 0 0 1-.5-.5v-1a.5.5 0 0 1 .5-.5zm-3-1A1.5 1.5 0 0 0 5 1.5v1A1.5 1.5 0 0 0 6.5 4h3A1.5 1.5 0 0 0 11 2.5v-1A1.5 1.5 0 0 0 9.5 0z"/>
                      </svg>
                    </a>
                  </form>
                </td>
              </tr>
            @else
            <tr>
              <td>No files yet!</td>
            </tr>
            @endfor
          </tbody>
        </table>
      </div>
    </section>
  </div>
`)