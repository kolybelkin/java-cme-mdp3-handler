/*
 * Copyright 2004-2016 EPAM Systems
 * This file is part of Java Market Data Handler for CME Market Data (MDP 3.0).
 * Java Market Data Handler for CME Market Data (MDP 3.0) is free software: you can redistribute it and/or modify it under the terms of the
 * GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 * Java Market Data Handler for CME Market Data (MDP 3.0) is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along with Java Market Data Handler for CME Market Data (MDP 3.0).
 * If not, see <http://www.gnu.org/licenses/>.
 */

package com.epam.cme.mdp3;

public interface VoidMBOChannelListener extends MBOChannelListener {
    @Override
    default void onFeedStarted(String channelId, FeedType feedType, Feed feed) {

    }

    @Override
    default void onFeedStopped(String channelId, FeedType feedType, Feed feed) {

    }

    @Override
    default void onPacket(String channelId, FeedType feedType, Feed feed, MdpPacket mdpPacket) {

    }

    @Override
    default void onBeforeChannelReset(String channelId, MdpMessage resetMessage) {

    }

    @Override
    default void onFinishedChannelReset(String channelId, MdpMessage resetMessage) {

    }

    @Override
    default void onChannelStateChanged(String channelId, ChannelState prevState, ChannelState newState) {

    }

    @Override
    default int onSecurityDefinition(String channelId, MdpMessage secDefMessage) {
        return MdEventFlags.NOTHING;
    }

    @Override
    default void onIncrementalMBORefresh(String channelId, short matchEventIndicator, int securityId, String secDesc, long msgSeqNum, FieldSet orderEntry, FieldSet mdEntry) {

    }

    @Override
    default void onSnapshotMBOFullRefresh(String channelId, String secDesc, MdpMessage snptMessage) {

    }

    @Override
    default void onRequestForQuote(String channelId, MdpMessage rfqMessage) {

    }

    @Override
    default void onSecurityStatus(String channelId, int securityId, MdpMessage secStatusMessage) {

    }
}
