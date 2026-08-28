# GOV.UK QA sandbox

Two projects:

- **`app/`** — a small Play Framework (Scala/Twirl) service implementing a
  short GOV.UK-style journey, styled with the real GOV.UK Design System
  CSS/JS. This exists purely to give the test suite something stable and
  self-contained to run against — a live public website's markup can (and
  does) change without notice, which makes it a poor fit for a suite meant
  to stay green while QAs practise refactoring the *tests*, not chase a
  moving target.
- **`tests/`** — the actual QA exercise: a Scala + Selenium test suite with
  plenty of room for improvement. See `tests/README.md` for the exercise
  framing, fork instructions, and how to run it.

## Quick start

This is a single sbt multi-project build — open the repo root in your
IDE/Metals, not the `app` or `tests` subfolders. There are two subprojects,
`app` and `tests`, both run from the root.

```
sbt app/run        # leave this running
```

In a second terminal, also from the repo root:

```
sbt tests/test
```

See `app/README.md` for notes on how the app was put together (it follows
HMRC's Play frontend conventions — see [github.com/hmrc](https://github.com/hmrc)
— without the `play-frontend-hmrc` component library, to keep the
dependency footprint small), and `tests/README.md` for the exercise itself.
