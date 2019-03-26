package de.thm.icampus.mdd.model.extensions

import de.thm.icampus.mdd.model.{Language, Manifest}
import de.thm.icampus.mdd.model.sql.{Entity}

case class ComponentExtension(name: String,
                              manifest: Manifest,
                              languages: Set[Language],
                              frontend: Frontend,
                              backend: Backend,
                              entities: List[Entity],
                              params: Set[JParamGroup] = Set.empty[JParamGroup],
                              ) extends Extension







