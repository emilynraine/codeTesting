public class RandomStub extends java.util.Random {
    private final int firstAnswer;
    private final int secondAnswer;
    private boolean firstAnswerNext;

    public RandomStub(int firstAnswer, int secondAnswer) {
        this.firstAnswer = firstAnswer;
        this.secondAnswer = secondAnswer;
        firstAnswerNext = true;
    }

    @Override
    public int nextInt() {
        final int answer = (firstAnswerNext ? firstAnswer : secondAnswer);
        firstAnswerNext = !firstAnswerNext;
        return answer;
    }
}

