package com.example.a1105;

import java.util.List;

public class Model {




        private boolean endOfWord;

        private int pos;

        private List<String> text = null;

        public boolean getEndOfWord() {
            return endOfWord;
        }

        public void setEndOfWord(boolean endOfWord) {
            this.endOfWord = endOfWord;
        }

        public int getPos() {
            return pos;
        }

        public void setPos(int pos) {
            this.pos = pos;
        }

        public List<String> getText() {
            return text;
        }

        public void setText(List<String> text) {
            this.text = text;
        }


}
