@import com.cbidici.site.view.data.ErrorDetailResponse
@import com.cbidici.site.view.data.Page
@param ErrorDetailResponse errorDetail

@template.base(
page = Page.builder().build(),
headElements = @`
  <title>Coşkun Bıdıcı's Sandbox - Error</title>
`,
content = @`
  <div>
    <section class="mb-3">
      <div class="shadow-sm mb-3 p-3 bg-white">
        <div class="card-body" style="height:20em; display: table; width:100%">
          <div style="vertical-align:middle; display: table-cell; text-align:center; width:100%">
            <h4>${errorDetail.status().toString()}</h4>
            <span>${errorDetail.message()}</span>
          </div>
        </div>
      </div>
    </section>
  </div>
`)

