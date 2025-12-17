package kr.hhplus.be.server.util.event;

public record CompletedEvent(Long id, Long userId, Long productId, int totalAmount) {}