package br.com.luizcanassa.projetointegrador.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EditOrderDTO {

    private Long id;

    private Long customerId;

    private int status;

    private List<Long> productIds;
}
