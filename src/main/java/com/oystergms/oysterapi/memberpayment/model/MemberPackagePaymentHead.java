package com.oystergms.oysterapi.memberpayment.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberPackagePaymentHead {

    private Integer gymMemberId;

    private String gymMemberFullName;

    private Double gymTotalPaidAmount;

    private Double gymTotalDueAmount;

}
