\version "2.24.0"

\header {
  title = "LilyPond Example"
  composer = "João Lima"
}

\score {
  \new Staff {
    \clef treble
    \key c \major
    \time 4/4

    c''4 d' e' f' |
    g'4 a' b' c'' |
    c''4 b' a' g' |
    f'4 e' d' c'
  }

  \layout { }
  \midi { }
}
