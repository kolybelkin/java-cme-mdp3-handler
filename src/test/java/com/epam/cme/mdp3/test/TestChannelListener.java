package com.epam.cme.mdp3.test;

import com.epam.cme.mdp3.*;
import com.epam.cme.mdp3.core.control.InstrumentState;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class TestChannelListener implements ChannelListener {
    private BlockingQueue<Pair<String,MdpMessage>> snapshotQueue = new LinkedBlockingQueue<>();
    private BlockingQueue<Pair<String,MdpMessage>> securitiesQueue = new LinkedBlockingQueue<>();

    private static final int WAITING_TIME_IN_MILLIS = 700;

    @Override
    public void onFeedStarted(String channelId, FeedType feedType, Feed feed) {

    }

    @Override
    public void onFeedStopped(String channelId, FeedType feedType, Feed feed) {

    }

    @Override
    public void onPacket(String channelId, FeedType feedType, Feed feed, MdpPacket mdpPacket) {

    }

    @Override
    public void onBeforeChannelReset(String channelId, MdpMessage resetMessage) {

    }

    @Override
    public void onFinishedChannelReset(String channelId, MdpMessage resetMessage) {

    }

    @Override
    public void onChannelStateChanged(String channelId, ChannelState prevState, ChannelState newState) {

    }

    @Override
    public void onInstrumentStateChanged(String channelId, int securityId, String secDesc, InstrumentState prevState, InstrumentState newState) {

    }

    @Override
    public int onSecurityDefinition(String channelId, MdpMessage secDefMessage) {
        securitiesQueue.add(new ImmutablePair<>(channelId, secDefMessage));
        return 0;
    }

    @Override
    public void onIncrementalRefresh(String channelId, short matchEventIndicator, int securityId, String secDesc, long msgSeqNum, FieldSet incrRefreshEntry) {

    }

    @Override
    public void onSnapshotFullRefresh(String channelId, String secDesc, MdpMessage snptMessage) {
        snapshotQueue.add(new ImmutablePair<>(channelId, snptMessage));
    }

    @Override
    public void onRequestForQuote(String channelId, MdpMessage rfqMessage) {

    }

    @Override
    public void onSecurityStatus(String channelId, int securityId, MdpMessage secStatusMessage) {

    }

    public Pair<String,MdpMessage> nextSnapshotMessage() throws InterruptedException {
        return snapshotQueue.poll(WAITING_TIME_IN_MILLIS, TimeUnit.MILLISECONDS);
    }

    public Pair<String,MdpMessage> nextSecurityMessage() throws InterruptedException {
        return securitiesQueue.poll(WAITING_TIME_IN_MILLIS, TimeUnit.MILLISECONDS);
    }

}