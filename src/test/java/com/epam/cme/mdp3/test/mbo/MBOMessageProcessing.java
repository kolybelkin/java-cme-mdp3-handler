package com.epam.cme.mdp3.test.mbo;

import com.epam.cme.mdp3.*;
import com.epam.cme.mdp3.core.channel.MdpChannelBuilder;
import com.epam.cme.mdp3.core.channel.MdpFeedContext;
import com.epam.cme.mdp3.core.control.MBOChannelController;
import com.epam.cme.mdp3.core.control.MBOChannelControllerImpl;
import com.epam.cme.mdp3.core.control.MBOInstrumentController;
import com.epam.cme.mdp3.sbe.schema.MdpMessageTypes;
import com.epam.cme.mdp3.test.ModelUtils;
import com.epam.cme.mdp3.test.TestChannelListener;
import org.junit.Before;
import org.junit.Test;

import java.nio.ByteBuffer;

import static org.junit.Assert.assertNotNull;

public class MBOMessageProcessing {
    public static final String TEMPLATE_NAME = "templates_FixBinary.xml";
    public static final String CONFIG_NAME = "config.xml";
    private TestChannelListener testListener = new TestChannelListener();
    private MdpChannel mdpChannel;
    private MdpMessageTypes mdpMessageTypes;

    @Before
    public void init() throws Exception {
        ClassLoader classLoader = getClass().getClassLoader();
        mdpMessageTypes = new MdpMessageTypes(classLoader.getResource(TEMPLATE_NAME).toURI());
        MdpChannelBuilder mdpHandlerBuilder = new MdpChannelBuilder(String.valueOf(648),
                classLoader.getResource(CONFIG_NAME).toURI(),
                classLoader.getResource(TEMPLATE_NAME).toURI())
                .usingListener(testListener);
        mdpChannel = mdpHandlerBuilder.build();
    }

    @Test
    public void handlerMustProcessAndResendMBOSnapshotMessageToClient() throws InterruptedException {
        final MdpFeedContext smboContext = new MdpFeedContext(Feed.A, FeedType.SMBO);
        final MdpFeedContext instrumentContext = new MdpFeedContext(Feed.A, FeedType.N);
        final int security = 99;
        ByteBuffer securityDefinition = ModelUtils.getMDInstrumentDefinitionFuture27(1, security);
        ByteBuffer mboSnapshotTestMessage = ModelUtils.getMBOSnapshotTestMessage(2, security);
        mdpChannel.subscribe(security, "Test security");
        final MdpPacket mdpPacketWithSecurityDefinition = MdpPacket.instance();
        mdpPacketWithSecurityDefinition.wrapFromBuffer(securityDefinition);
        mdpChannel.handlePacket(instrumentContext, mdpPacketWithSecurityDefinition);
        assertNotNull(testListener.nextSecurityMessage());

        final MdpPacket mdpPacketWithSnapshot = MdpPacket.instance();
        mdpPacketWithSnapshot.wrapFromBuffer(mboSnapshotTestMessage);
        mdpChannel.handlePacket(smboContext, mdpPacketWithSnapshot);
        assertNotNull(testListener.nextSnapshotMessage());
    }

    @Test
    public void test(){
        final MdpPacket mdpPacketWithSnapshot = MdpPacket.instance();
        final MdpFeedContext smboContext = new MdpFeedContext(Feed.A, FeedType.SMBO);
        ByteBuffer mboSnapshotTestMessage = ModelUtils.getMBOSnapshotTestMessage(1, 99);
        mdpPacketWithSnapshot.wrapFromBuffer(mboSnapshotTestMessage);
        MBOChannelController mboChannelController = new MBOChannelControllerImpl(securityId -> new MBOInstrumentController(), mdpMessageTypes);
        mboChannelController.handleSnapshotPacket(smboContext, mdpPacketWithSnapshot);
    }



}