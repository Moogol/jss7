/*
 * JBoss, Home of Professional Open Source
 * Copyright 2011, Red Hat, Inc. and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */

package org.mobicents.protocols.ss7.cap.EsiBcsm;

import static org.testng.Assert.*;

import java.util.Arrays;

import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.ss7.cap.EsiBcsm.TBusySpecificInfoImpl;
import org.mobicents.protocols.ss7.cap.api.isup.CalledPartyNumberCap;
import org.mobicents.protocols.ss7.cap.api.isup.CauseCap;
import org.mobicents.protocols.ss7.cap.isup.CalledPartyNumberCapImpl;
import org.mobicents.protocols.ss7.cap.isup.CauseCapImpl;
import org.mobicents.protocols.ss7.cap.service.circuitSwitchedCall.primitive.EventSpecificInformationBCSMImpl;
import org.mobicents.protocols.ss7.isup.impl.message.parameter.CalledPartyNumberImpl;
import org.mobicents.protocols.ss7.isup.impl.message.parameter.CauseIndicatorsImpl;
import org.mobicents.protocols.ss7.isup.message.parameter.CalledPartyNumber;
import org.mobicents.protocols.ss7.isup.message.parameter.CauseIndicators;
import org.testng.*;import org.testng.annotations.*;

/**
 * 
 * @author sergey vetyutnev
 * 
 */
public class TBusySpecificInfoTest {

	public byte[] getData1() {
		return new byte[] { (byte) 168, 20, (byte) 128, 2, (byte) 132, (byte) 144, (byte) 159, 50, 0, (byte) 159, 51, 0, (byte) 159, 52, 7, (byte) 128,
				(byte) 144, 17, 33, 34, 51, 3 };
	}

	@Test(groups = { "functional.decode","circuitSwitchedCall.primitive"})
	public void testDecode() throws Exception {

		byte[] data = this.getData1();
		AsnInputStream ais = new AsnInputStream(data);
		EventSpecificInformationBCSMImpl elem = new EventSpecificInformationBCSMImpl();
		int tag = ais.readTag();
		elem.decodeAll(ais);
		CauseIndicators ci = elem.getTBusySpecificInfo().getBusyCause().getCauseIndicators();
		assertEquals(ci.getCauseValue(), 16);
		assertEquals(ci.getCodingStandard(), 0);
		assertEquals(ci.getLocation(), 4);
		assertTrue(elem.getTBusySpecificInfo().getCallForwarded());
		assertTrue(elem.getTBusySpecificInfo().getRouteNotPermitted());
		CalledPartyNumberCap fdn = elem.getTBusySpecificInfo().getForwardingDestinationNumber();
		CalledPartyNumber cpn = fdn.getCalledPartyNumber();
		assertTrue(cpn.getAddress().endsWith("111222333"));
		assertEquals(cpn.getNatureOfAddressIndicator(), 0);
		assertEquals(cpn.getNumberingPlanIndicator(), 1);
		assertEquals(cpn.getInternalNetworkNumberIndicator(), 1);
	}

	@Test(groups = { "functional.encode","circuitSwitchedCall.primitive"})
	public void testEncode() throws Exception {

		CauseIndicators causeIndicators = new CauseIndicatorsImpl(0, 4, 16, null);
		CauseCap busyCause = new CauseCapImpl(causeIndicators);
		CalledPartyNumberImpl calledPartyNumber = new CalledPartyNumberImpl(0, "111222333", 1, 1);
		CalledPartyNumberCapImpl forwardingDestinationNumber = new CalledPartyNumberCapImpl(calledPartyNumber);
		TBusySpecificInfoImpl tBusySpecificInfo = new TBusySpecificInfoImpl(busyCause, true, true, forwardingDestinationNumber);
		EventSpecificInformationBCSMImpl elem = new EventSpecificInformationBCSMImpl(tBusySpecificInfo);
		AsnOutputStream aos = new AsnOutputStream();
		elem.encodeAll(aos);
		assertTrue(Arrays.equals(aos.toByteArray(), this.getData1()));
		
		// int natureOfAddresIndicator, String address, int numberingPlanIndicator, int internalNetworkNumberIndicator
		// CauseCap busyCause, boolean callForwarded, boolean routeNotPermitted, CalledPartyNumberCap forwardingDestinationNumber
	}
}

