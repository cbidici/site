@import com.cbidici.site.view.data.Page
@param Page page

@template.base(
page = page,
headElements = @`
  <link href="/css/profile.css" rel="stylesheet">
  <title>About Coşkun Bıdıcı</title>
`,
content = @`
  <div>
    <section class="mb-3">
      <div class="shadow-sm mb-3 p-3 bg-white">
        <div class="card-body">
          <div class="row">
            <div class="col-lg-4">
              <div class="profile-pic">
                <img src="/images/coskun_bidici_profile.jpg" alt="Coşkun Bıdıcı">
              </div>
            </div>
            <div class="col-lg-8 vertical-align">
              <ul>
                <li>15+ years experience on Software Engineering</li>
                <li>4+ years experience on engineering management</li>
                <li>Experience in highly scalable distributed web applications</li>
                <li>B.Sc. Computer Science & Engineering</li>
                <li>Worked on domains such as IOT, E-Learning, E-Commerce, OMS, Telecom, BI, CRM, Marketplace</li>
                <li>Motivated by being productive</li>
              </ul>
            </div>
          </div>
        </div>
      </div>
    </section>
  </div>
`)