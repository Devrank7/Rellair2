package org.rellair2.com.api.temperature;

public interface IArrowDrawer {
    public void setDirection(ArrowDirection direction);
    public ArrowDirection getDirection();
    public int getDirectionIndex();
    public enum ArrowDirection {
        UP(224),
        DOWN(240);

        private final int v;

        ArrowDirection(int v) {
            this.v = v;
        }

        public int getU(int frame) {
            return frame * 16;
        }

        public int getV() {
            return this.v;
        }
    }
}
