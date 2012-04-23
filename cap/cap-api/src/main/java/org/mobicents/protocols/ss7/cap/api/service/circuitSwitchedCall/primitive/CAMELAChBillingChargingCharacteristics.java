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

package org.mobicents.protocols.ss7.cap.api.service.circuitSwitchedCall.primitive;

import org.mobicents.protocols.ss7.cap.api.primitives.CAPExtensions;

/**
*
AChBillingChargingCharacteristics {PARAMETERS-BOUND : bound} ::= OCTET STRING (SIZE
(bound.&minAChBillingChargingLength .. bound.&maxAChBillingChargingLength))
(CONSTRAINED BY {-- shall be the result of the BER-encoded value of the type --
CAMEL-AChBillingChargingCharacteristics {bound}})
-- The AChBillingChargingCharacteristics parameter specifies the charging related information
-- to be provided by the gsmSSF and the conditions on which this information has to be reported
-- back to the gsmSCF with the ApplyChargingReport operation. The value of the
-- AChBillingChargingCharacteristics of type OCTET STRING carries a value of the ASN.1 data type:
-- CAMEL-AChBillingChargingCharacteristics. The normal encoding rules are used to encode this
-- value.
-- The violation of the UserDefinedConstraint shall be handled as an ASN.1 syntax error.

CAMEL-AChBillingChargingCharacteristics {PARAMETERS-BOUND : bound} ::= CHOICE {
timeDurationCharging [0] SEQUENCE {
maxCallPeriodDuration [0] INTEGER (1..864000),
releaseIfdurationExceeded [1] BOOLEAN DEFAULT FALSE,
tariffSwitchInterval [2] INTEGER (1..86400) OPTIONAL,
audibleIndicator [3] AudibleIndicator DEFAULT tone: FALSE,
extensions [4] Extensions {bound} OPTIONAL,
...
}
}
-- tariffSwitchInterval is measured in 1 second units.
-- maxCallPeriodDuration is measured in 100 millisecond units

* 
* @author sergey vetyutnev
* 
*/
public interface CAMELAChBillingChargingCharacteristics {

	public byte[] getData();

	public long getMaxCallPeriodDuration();

	public boolean getReleaseIfdurationExceeded();

	public Long getTariffSwitchInterval();

	public AudibleIndicator getAudibleIndicator();

	public CAPExtensions getExtensions();

}
