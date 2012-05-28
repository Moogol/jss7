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

package org.mobicents.protocols.ss7.map.primitives;

import java.io.IOException;

import org.mobicents.protocols.asn.AsnException;
import org.mobicents.protocols.asn.AsnInputStream;
import org.mobicents.protocols.asn.AsnOutputStream;
import org.mobicents.protocols.asn.Tag;
import org.mobicents.protocols.ss7.map.api.MAPException;
import org.mobicents.protocols.ss7.map.api.MAPParsingComponentException;
import org.mobicents.protocols.ss7.map.api.MAPParsingComponentExceptionReason;
import org.mobicents.protocols.ss7.map.api.primitives.IMSI;

/**
 * 
 * @author sergey vetyutnev
 * 
 */
public class IMSIImpl extends TbcdString implements IMSI {

	private String data;

	
	public IMSIImpl() {
	}
	
	public IMSIImpl(String data) {
		this.data = data;
	}

	public String getData() {
		return this.data;
	}

	
	public int getTag() throws MAPException {
		return Tag.STRING_OCTET;
	}

	public int getTagClass() {
		return Tag.CLASS_UNIVERSAL;
	}

	public boolean getIsPrimitive() {
		return true;
	}
	

	public void decodeAll(AsnInputStream ansIS) throws MAPParsingComponentException {
		try {
			int length = ansIS.readLength();
			this._decode(ansIS, length);
		} catch (IOException e) {
			throw new MAPParsingComponentException("IOException when decoding IMSI: " + e.getMessage(), e,
					MAPParsingComponentExceptionReason.MistypedParameter);
		}
	}

	public void decodeData(AsnInputStream ansIS, int length) throws MAPParsingComponentException {
		try {
			this._decode(ansIS, length);
		} catch (IOException e) {
			throw new MAPParsingComponentException("IOException when decoding IMSI: " + e.getMessage(), e,
					MAPParsingComponentExceptionReason.MistypedParameter);
		}
	}

	private void _decode(AsnInputStream ansIS, int length) throws MAPParsingComponentException, IOException {

		if (length < 3 || length > 8)
			throw new MAPParsingComponentException("Error decoding IMSI: the IMSI field must contain from 3 to 8 octets. Contains: " + length,
					MAPParsingComponentExceptionReason.MistypedParameter);

		try {
			String res = this.decodeString(ansIS, length);
			this.data = res;

//			String sMcc = res.substring(0, 3);
//			String sMnc = res.substring(3, 5);
//			this.MSIN = res.substring(5);
//
//			this.MCC = (long) Integer.parseInt(sMcc);
//			this.MNC = (long) Integer.parseInt(sMnc);
		} catch (IOException e) {
			throw new MAPParsingComponentException("IOException when decoding IMSI: " + e.getMessage(), e,
					MAPParsingComponentExceptionReason.MistypedParameter);
		}
	}	
	
	public void encodeAll(AsnOutputStream asnOs) throws MAPException {
		
		this.encodeAll(asnOs, Tag.CLASS_UNIVERSAL, Tag.STRING_OCTET);
	}

	public void encodeAll(AsnOutputStream asnOs, int tagClass, int tag) throws MAPException {
		
		try {
			asnOs.writeTag(tagClass, true, tag);
			int pos = asnOs.StartContentDefiniteLength();
			this.encodeData(asnOs);
			asnOs.FinalizeContent(pos);
		} catch (AsnException e) {
			throw new MAPException("AsnException when encoding IMSI: " + e.getMessage(), e);
		}
	}

	public void encodeData(AsnOutputStream asnOs) throws MAPException {

		if (this.data == null)
			throw new MAPException("Error while encoding the IMSI: data is not defined");


//		if (this.MCC == null || this.MNC == null || this.MSIN == null)
//			throw new MAPException("Error while encoding the IMSI: MMC, MNC or MSIN is not defined");
//
//		if (this.MCC < 0 || this.MCC > 999)
//			throw new MAPException("Error while encoding the IMSI: Bad MCC value");
//		if (this.MNC < 0 || this.MNC > 99)
//			throw new MAPException("Error while encoding the IMSI: Bad MNC value");
//		if (this.MSIN.length() < 1 || this.MSIN.length() > 11)
//			throw new MAPException("Error while encoding the IMSI: Bad MSIN value");
//
//		StringBuilder sb = new StringBuilder();
//		if (this.MCC < 100)
//			sb.append("0");
//		if (this.MCC < 10)
//			sb.append("0");
//		sb.append(this.MCC);
//		if (this.MNC < 10)
//			sb.append("0");
//		sb.append(this.MNC);
//		sb.append(this.MSIN);

		this.encodeString(asnOs, this.data);
	}

	
	@Override
	public String toString() {
		return "IMSI [" + this.data + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		IMSIImpl other = (IMSIImpl) obj;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		return true;
	}
}
