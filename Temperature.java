package temperature;

public class Temperature {
  public static int getRandomNumber() {
    int max = 45;
    int min = 15;

    return (int) ((Math.random() * (max - min)) + min);
  }
}