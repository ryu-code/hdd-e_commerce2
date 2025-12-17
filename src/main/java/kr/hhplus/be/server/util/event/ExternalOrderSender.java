package kr.hhplus.be.server.util.event;

public interface ExternalOrderSender {
    void send(CompletedEvent event);
}
