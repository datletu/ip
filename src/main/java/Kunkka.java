public class Kunkka {
    public static void main(String[] args) {
        String horizontalLine = "____________________________________________________________";
        String name = "Kunkka";
        String greeting = "Ahoy! I'm " + name + "\n" + "What can I do for you?";
        String farewell = "Farewell, matey! May the wind be at your back!";
        System.out.println(horizontalLine + "\n" + greeting + "\n" + horizontalLine + "\n");

        String input = new java.util.Scanner(System.in).nextLine();
        while (!input.equals("bye")) {
            String output = horizontalLine + "\n" + input + "\n" + horizontalLine + "\n";
            System.out.println(output);
            input = new java.util.Scanner(System.in).nextLine();
        }
        System.out.println(horizontalLine + "\n" + farewell + "\n" + horizontalLine);
    }
}
