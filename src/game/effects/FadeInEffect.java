package game.effects;

public class FadeInEffect implements Runnable{
    private final FadeEffectApplicable _parent;

    public FadeInEffect(FadeEffectApplicable parentUI) {
        _parent = parentUI;
    }

    @Override
    public void run() {
        float currentAlpha = _parent.getAlpha();

        // Speed is only limited by CPU, thus actual fading speed can vary depending on cpu speed. Can be solved with a limiter, just not today.
        while (currentAlpha < 1) {
            _parent.setAlpha(currentAlpha + _parent.getFadingSpeed());
            currentAlpha = _parent.getAlpha();
        }
    }
}
