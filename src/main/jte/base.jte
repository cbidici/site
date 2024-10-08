@import gg.jte.Content
@import com.cbidici.site.view.data.Page

@param Page page
@param Content headElements
@param Content content

<!DOCTYPE html>
<html lang="en">
<head>
  @if(headElements!= null)
    ${headElements}
  @else
    <title>Coşkun Bıdıcı's Sandbox</title>
    <meta name="description" content="Self developed, hosted and authored software engineering blog by Coşkun Bıdıcı">
  @endif

  <script async src="https://www.googletagmanager.com/gtag/js?id=G-8PLPHEQBGX"></script>
  <script>
    window.dataLayer = window.dataLayer || [];
    function gtag(){dataLayer.push(arguments);}
    gtag('js', new Date());

    gtag('config', 'G-8PLPHEQBGX');
  </script>
  <meta charset="utf-8">
  <meta name="keywords" content="Software Engineering Blog, Blog, Portfolio">
  <meta name="author" content="Coşkun Bıdıcı">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <link href="/css/base.css" rel="stylesheet">
  <link rel="icon" href="/images/favicon.svg">
</head>
<body>
<div>
  <nav class="navbar navbar-expand-lg sticky-top shadow-sm p-3 mb-3 bg-white">
    <div class="container-sm">
      <a class="navbar-brand" href="/"><i class="svg-logo"></i> Coşkun Bıdıcı's Sandbox</a>
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="customized navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
          <li class="nav-item"><a class="nav-link ${page.isCurrentUri("/") ? "active text-decoration-underline" : ""}" href="/">Home</a></li>
          <li class="nav-item"><a class="nav-link ${page.isCurrentUri("/posts") ? "active text-decoration-underline" : ""}" href="/posts">Posts</a></li>
          <li class="nav-item"><a class="nav-link ${page.isCurrentUri("/about") ? "active text-decoration-underline" : ""}" href="/about">About</a></li>
        </ul>
        <div class="d-flex">
          <ul class="navbar-nav me-auto mb-2 mb-lg-0">
            <li class="nav-item">
              @if(page.hasRole("ADMIN"))
                <a class="nav-link @if(page.isCurrentUri("/storage")) active text-decoration-underline @endif" href="/storage">Storage</a>
              @endif
            </li>
            <li class="nav-item">
              @if(!page.isAuthenticated())
                <a class="nav-link @if(page.isCurrentUri("/login")) active text-decoration-underline @endif" href="/login">Login</a>
              @else
                <form action="/logout" method="post">
                  <input type="hidden" name="_csrf" value="${page.getCsrfToken()}">
                  <button type="submit" class="nav-link">Logout</button>
                </form>
              @endif
            </li>
          </ul>
        </div>
      </div>
    </div>
  </nav>
  <div class="container-sm">
    @if(content != null)
      ${content}
    @else
      Coming soon...
    @endif
  </div>
  <div class="container-sm mt-5 mb-5">
    <div class="footer-line">
      <a href="https://github.com/cbidici" aria-label="GitHub" target="_blank" rel="noopener noreferrer">
        <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor"
             class="bi bi-github" viewBox="0 0 16 16">
          <path
                  d="M8 0C3.58 0 0 3.58 0 8c0 3.54 2.29 6.53 5.47 7.59.4.07.55-.17.55-.38 0-.19-.01-.82-.01-1.49-2.01.37-2.53-.49-2.69-.94-.09-.23-.48-.94-.82-1.13-.28-.15-.68-.52-.01-.53.63-.01 1.08.58 1.23.82.72 1.21 1.87.87 2.33.66.07-.52.28-.87.51-1.07-1.78-.2-3.64-.89-3.64-3.95 0-.87.31-1.59.82-2.15-.08-.2-.36-1.02.08-2.12 0 0 .67-.21 2.2.82.64-.18 1.32-.27 2-.27.68 0 1.36.09 2 .27 1.53-1.04 2.2-.82 2.2-.82.44 1.1.16 1.92.08 2.12.51.56.82 1.27.82 2.15 0 3.07-1.87 3.75-3.65 3.95.29.25.54.73.54 1.48 0 1.07-.01 1.93-.01 2.2 0 .21.15.46.55.38A8.012 8.012 0 0 0 16 8c0-4.42-3.58-8-8-8z"/>
        </svg>
      </a>
      <a href="https://www.linkedin.com/in/cbidici/" aria-label="LinkedIn" target="_blank" rel="noopener noreferrer">
        <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor"
             class="bi bi-linkedin" viewBox="0 0 16 16">
          <path d="M0 1.146C0 .513.526 0 1.175 0h13.65C15.474 0 16 .513 16 1.146v13.708c0 .633-.526 1.146-1.175 1.146H1.175C.526 16 0 15.487 0 14.854V1.146zm4.943 12.248V6.169H2.542v7.225h2.401zm-1.2-8.212c.837 0 1.358-.554 1.358-1.248-.015-.709-.52-1.248-1.342-1.248-.822 0-1.359.54-1.359 1.248 0 .694.521 1.248 1.327 1.248h.016zm4.908 8.212V9.359c0-.216.016-.432.08-.586.173-.431.568-.878 1.232-.878.869 0 1.216.662 1.216 1.634v3.865h2.401V9.25c0-2.22-1.184-3.252-2.764-3.252-1.274 0-1.845.7-2.165 1.193v.025h-.016a5.54 5.54 0 0 1 .016-.025V6.169h-2.4c.03.678 0 7.225 0 7.225h2.4z"/>
        </svg>
      </a>
    </div>
  </div>
</div>
<script async src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js"></script>
</body>
</html>
