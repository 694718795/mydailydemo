package com.shu.dao;

import com.shu.po.CascadeSubNode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CascadeSubNodeRepository extends JpaRepository<CascadeSubNode, Long>, JpaSpecificationExecutor {
}
