package com.arpangroup.onboarding.schema;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class OperationalDetails {
    private List<Period> timings;
}
