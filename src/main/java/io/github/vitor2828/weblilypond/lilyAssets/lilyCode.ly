\version "2.24.0"

\header {
  title = "Teste LilyPond"
  subtitle = "Escala de Dó maior"
  composer = "WebLilypond"
}

\score {
  \new Staff {
    \clef treble
    \key c \major
    \time 4/4

    c'4 d' e' f'
    g' a' b' c''

    c'' b' a' g'
    f' e' d' c'
  }

  \layout { }
  \midi { }
}