package studio.asmussen;

import discord4j.core.DiscordClientBuilder;
import discord4j.core.event.domain.lifecycle.ReadyEvent;
import discord4j.core.object.entity.User;
import lombok.Data;
import java.util.Objects;

import discord4j.core.DiscordClient;
import discord4j.core.GatewayDiscordClient;
import reactor.core.publisher.Mono;

@Data
public class Bot {

    private DiscordClient client;
    private String name;

    public Bot(String token) {

        this.client = DiscordClient.create(token);

        Mono<Void> login = client.withGateway((GatewayDiscordClient gateway) ->
                gateway.on(ReadyEvent.class, event ->
                        Mono.fromRunnable(() -> {

                            final User SELF = event.getSelf();
                            System.out.printf("Logged in as %s#%s!%n", SELF.getUsername(), SELF.getDiscriminator());
                        })
                )
        );

        login.block();
    }
}
