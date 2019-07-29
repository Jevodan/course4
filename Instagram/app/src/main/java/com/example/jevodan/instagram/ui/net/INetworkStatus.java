package com.example.jevodan.instagram.ui.net;

public interface INetworkStatus {

    enum Status {
        WIFI,
        MOBILE,
        ETHERNET,
        OTHER,
        OFFLINE,
    }

    Status getStatus();

    boolean isOnline();

    boolean isWifi();

    boolean isEthernet();

    boolean isMobile();

    boolean isOffline();
}
