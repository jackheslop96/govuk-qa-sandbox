# GOV.UK example journey (Play / Scala / Twirl)

A small four-page Play Framework service styled with the real
[GOV.UK Design System](https://design-system.service.gov.uk/) CSS/JS
(loaded from a CDN), used as a stable local target for the Selenium
suite in `../tests`.

The journey:

1. `/` — What is your name? (text input)
2. `/date-of-birth` — What is your date of birth? (day / month / year)
3. `/nationality` — What is your nationality? (radios, with a conditional
   text reveal for "Other")
4. `/marital-status` — What is your marital status? (select / dropdown)
5. `/address` — What is your address? (several text inputs on one page)
6. `/phone-number` — What is your phone number? (single, optional text
   input)
7. `/hobbies` — What are your hobbies? (checkboxes, with a conditional
   text reveal for "Other")
8. `/about-you` — Tell us a bit about yourself (textarea)
9. `/email-choice` — Would you like to provide an email address? (yes/no)
10. `/email-address` — What is your email address? (only shown if "yes")
11. `/confirmation` — summary of everything entered

Answers are held in the Play session cookie. Each page (other than the
first) checks that the previous step has been completed and redirects
back if not, so the journey can't be skipped by jumping straight to a URL.

## Running it

Requires JDK 11+ and [sbt](https://www.scala-sbt.org/download.html).

This app is one subproject in a root-level multi-project build — run from
the repo root, not from inside `app/`:

```
sbt app/run
```

Then visit http://localhost:9000. Stop it with `Ctrl+C`.

## Notes on how this was built

This follows the conventions used across HMRC's Play frontend
microservices (see [github.com/hmrc](https://github.com/hmrc)) — one
controller per page, `play.api.data.Form` mappings, redirect-after-POST,
session-driven state, and markup using the same CSS class names as
`govuk-frontend`. Two simplifications versus a real HMRC service, both
called out here rather than left silent:

- **No `play-frontend-hmrc` dependency.** A real HMRC service would pull
  in [`play-frontend-hmrc`](https://github.com/hmrc/play-frontend-hmrc)
  and build pages from its Twirl components (`GovukInput`, `GovukRadios`,
  `GovukErrorSummary`, etc.) rather than hand-written HTML. This app
  writes the GOV.UK Design System markup directly instead, to keep the
  dependency footprint small for a sandbox exercise. Swapping the
  hand-written markup for the real component library is a legitimate,
  realistic refactor if you want to extend this.
- **CSRF protection is disabled** (`conf/application.conf`) to keep form
  submission simple for both the app and the tests driving it. A
  production service must not do this.
- **The hobbies checkboxes (`/hobbies`) are bound manually from the raw
  request body** rather than through a `play.api.data.Form` mapping. Play's
  `list()`/`seq()` combinators expect indexed keys (`hobbies[0]`,
  `hobbies[1]`), but real HTML checkboxes sharing one `name` attribute
  submit as repeated `hobbies=x&hobbies=y` pairs — so the controller reads
  `request.body.asFormUrlEncoded` directly instead. Every other page uses
  the standard `Form` mapping idiom.
