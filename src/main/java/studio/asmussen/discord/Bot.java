package studio.asmussen.discord;

import discord4j.common.util.Snowflake;
import discord4j.core.DiscordClient;

import discord4j.core.GatewayDiscordClient;
import lombok.Data;
import reactor.core.publisher.Mono;

import java.util.Objects;

public class Bot {

    private DiscordClient client;

    private String clientID;
    private String ownerID;

    public Bot(String token, String ownerID) {

        this.ownerID = ownerID;
        this.client = DiscordClient.create(token);
        this.clientID = Objects.requireNonNull(this.client.getSelf().block()).id().asString();

        if (this.ownerID != null) System.out.println("Owner has been identified as " + this.client.getUserById(Snowflake.of(this.clientID)) + ".");

        Mono<Void> login = client.withGateway((GatewayDiscordClient gateway) -> Mono.empty());

        login.block();
    }

    public static void main(String[] args) {

        final String TOKEN = System.getenv("DISCORD_TOKEN");
        final String OWNER_ID = System.getenv("DISCORD_OWNER_ID");

        if (TOKEN == null) throw new RuntimeException("Please set the DISCORD_TOKEN environment variable!");

        new Bot(TOKEN, OWNER_ID);
    }
}
