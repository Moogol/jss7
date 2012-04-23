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

package org.mobicents.protocols.ss7.map.service.callhandling;

import static org.testng.Assert.*;

import java.util.Arrays;

import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.testng.*;import org.testng.annotations.*;

/**
 * 
 * @author sergey vetyutnev
 * 
 */
public class CallReferenceNumberTest {

	public byte[] getData() {
		return new byte[] { 4, 5, 19, -6, 61, 61, -22 };
	};

	public byte[] getDataVal() {
		return new byte[] { 19, -6, 61, 61, -22 };
	};
	
	@Test(groups = { "functional.decode", "primitives" })
	public void testDecode() throws Exception {

		byte[] data = this.getData();

		AsnInputStream asn = new AsnInputStream(data);
		int tag = asn.readTag();

		CallReferenceNumberImpl prim = new CallReferenceNumberImpl();
		prim.decodeAll(asn);

		assertNotNull(prim.getData());
		assertTrue(Arrays.equals(getDataVal(), prim.getData()));		
		
	}
	
	@Test(groups = { "functional.decode", "primitives" })
	public void testEncode() throws Exception {

		CallReferenceNumberImpl prim = new CallReferenceNumberImpl(getDataVal());

		AsnOutputStream asn = new AsnOutputStream();
		prim.encodeAll(asn);

		assertTrue(Arrays.equals(asn.toByteArray(), this.getData()));
	}
}

