@import com.cbidici.site.view.data.ErrorDetailResponse
@import com.cbidici.site.view.data.Page
@import org.springframework.http.HttpStatus

@param ErrorDetailResponse errorDetail
@param String error

@template.base(
page = Page.builder().build(),
headElements = @`
  <title>Coşkun Bıdıcı's Sandbox - Error</title>
  <meta name="description" content="Self developed, hosted and authored software engineering blog by Coşkun Bıdıcı">
`,
content = @`
  <div>
    <section class="mb-3">
      <div class="shadow-sm mb-3 p-3 bg-white">
        <div class="card-body" style="height:20em; display: table; width:100%">
          <div style="vertical-align:middle; display: table-cell; text-align:center; width:100%">
            @if(errorDetail != null)
              <h4>${errorDetail.status().toString()}</h4>
              <span>${errorDetail.message()}</span>
            @elseif(error != null)
              <h4>${HttpStatus.INTERNAL_SERVER_ERROR.toString()}</h4>
              <span>Sorry! An unexpected error occurred. Please try again later.</span>
            @endif
          </div>
        </div>
      </div>
    </section>
  </div>
`)

