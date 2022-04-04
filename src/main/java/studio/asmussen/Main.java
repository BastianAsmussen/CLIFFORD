package studio.asmussen;

public class Main {

    public static void main(String[] args) {

        if (System.getenv("DISCORD_TOKEN") == null) {

            System.out.println("Please set the DISCORD_TOKEN environment variable.");
            System.exit(1);
        }

        final String TOKEN = System.getenv("DISCORD_TOKEN");

        Bot bot = new Bot(TOKEN);

    }
}
