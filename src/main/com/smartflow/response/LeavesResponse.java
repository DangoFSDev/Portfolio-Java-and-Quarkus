package main.com.smartflow.response;

import java.time.Instant;

import main.com.smartflow.model.BaseModel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LeavesResponse extends BaseModel {

    private Long id;
    private String description;
    private Instant startDate;
    private Instant endDate;

}
