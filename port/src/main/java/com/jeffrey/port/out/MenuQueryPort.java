package com.jeffrey.port.out;

import com.jeffrey.domain.Menu;

public interface MenuQueryPort {

    Menu findByMenuId(Long menuId);

}
