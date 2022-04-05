package studio.asmussen.discord;

import discord4j.core.DiscordClient;

import lombok.Data;

import java.util.Objects;

@Data
public class Bot {

    private DiscordClient client;

    private String clientID;
    private String ownerID;

    public Bot(String token, String ownerID) {

        this.ownerID = ownerID;
        this.client = DiscordClient.create(token);
        this.clientID = Objects.requireNonNull(client.getSelf().block()).id().asString();
    }

    public static void main(String[] args) {

        final String TOKEN = System.getenv("DISCORD_TOKEN");
        final String OWNER_ID = System.getenv("DISCORD_OWNER_ID");

        if (TOKEN == null) throw new RuntimeException("Please set the DISCORD_TOKEN environment variable!");

        new Bot(TOKEN, OWNER_ID);
    }
}
