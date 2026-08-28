# GOV.UK journey Selenium sandbox

A small Scala + Selenium test suite driving a local GOV.UK-style
journey (see `../app`), used as a **practice sandbox** for QAs — not a
reference implementation.

The suite currently passes and does its job — but it was written quickly,
without much thought for structure, maintainability, or long-term reliability.
That's intentional. It's here so QAs can fork it, dig in, and practise turning
"code that works" into "code you'd be happy to own."

## What you're looking at

- Scala 2.13
- [ScalaTest](https://www.scalatest.org/) (`AnyFlatSpec` style)
- [Selenium WebDriver](https://www.selenium.dev/) (Java bindings)
- [WebDriverManager](https://github.com/bonigarcia/webdrivermanager) to
  automatically fetch the right ChromeDriver version
- Eleven test specs under `src/test/scala/govuk/`, one per page plus a full
  end-to-end happy path, covering every common GOV.UK input type: single
  and multi-field text inputs, a date input, simple radios, radios with a
  conditional reveal, checkboxes with a conditional reveal, a select
  dropdown, and a textarea
- A shared helper object under `src/test/scala/govuk/utils/`
- A basic GitHub Actions workflow under `.github/workflows/`

## Getting started

### Prerequisites

- JDK 11+
- [sbt](https://www.scala-sbt.org/download.html)
- Google Chrome installed locally

### Forking the repo

1. Click **Fork** on GitHub to create your own copy under your account.
2. Clone your fork:
   ```
   git clone https://github.com/<your-username>/govuk-qa-sandbox.git
   cd govuk-qa-sandbox
   ```
3. (Optional but recommended) Create a branch for your changes:
   ```
   git checkout -b refactor/my-improvements
   ```
4. Make your changes, commit, and push to your fork as normal. There's no
   need to open a PR back to the original repo — this is your copy to
   experiment with.

### Running the tests

This is one sbt multi-project build rooted one level up — run these
commands from the repo root, not from inside `tests/`.

This suite drives a real, running instance of the app in `../app`, so start
that first, in its own terminal, from the repo root:

```
sbt app/run
```

Wait until it logs that it's listening on port 9000, then leave it running
and, in a second terminal, also from the repo root:

```
sbt tests/test
```

This will compile the project, download a matching ChromeDriver binary, and
launch a visible Chrome window that drives itself through the journey at
http://localhost:9000. Don't be alarmed when a browser window pops up and
starts filling in forms — that's expected.

To run a single spec:

```
sbt "tests/testOnly govuk.EmailAddressSpec"
```

## The exercise

Everything in this repo runs and passes, but that's a low bar. Have a look
through the code with a critical eye, the same way you would in a real
review, and treat anything that gives you pause as fair game to change.
Broadly, we'd like you to think about three things:

- **Refactoring** — is the code organised in a way that would scale to
  50 tests instead of 10? Is anything doing more than one job? Is the
  same multi-step navigation copy-pasted in more than one place?
- **Optimisation** — is the suite doing more work than it needs to, or
  waiting longer than it needs to?
- **Test robustness** — how would these tests hold up if a page's copy or
  markup changed slightly? What happens if a test fails halfway through —
  does everything clean up properly? Are any assertions checking something
  more specific (or less specific) than they should be?

There's no fixed answer key and no need to fix everything — pick what you
think matters most and be ready to explain your reasoning and trade-offs.

## A hint, if you're looking for a bigger structural project

The journey was deliberately built with a spread of GOV.UK input types —
plain text inputs, a multi-field page, a date input, simple radios, radios
with a conditional reveal, checkboxes with a conditional reveal, a select
dropdown, and a textarea. If you're after something more
structural than fixing individual specs, this variety is well suited to a
class hierarchy of page objects — something like a common base for "a GOV.UK
question page" with shared behaviour (error summary detection, clicking
Continue), and more specific subtypes for each shape of question. How you
draw those lines is exactly the kind of design decision worth making
yourself rather than being handed.

## A note on the CI workflow

The GitHub Actions workflow starts the app and immediately runs the tests
against it, with no check that the app has actually finished starting up
first. That's a real gap, not a hidden feature of the exercise — if you're
looking at the CI setup as part of your review, this is a legitimate thing
to fix.
