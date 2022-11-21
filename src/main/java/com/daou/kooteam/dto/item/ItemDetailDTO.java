package com.daou.kooteam.dto.item;

import com.daou.kooteam.model.Item;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemDetailDTO {
    Item item;
    boolean wishCheck;
}
