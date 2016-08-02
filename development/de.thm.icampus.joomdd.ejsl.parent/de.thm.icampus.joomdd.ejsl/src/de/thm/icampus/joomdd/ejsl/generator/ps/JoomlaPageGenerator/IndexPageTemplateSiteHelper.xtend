package de.thm.icampus.joomdd.ejsl.generator.ps.JoomlaPageGenerator

import de.thm.icampus.joomdd.ejsl.eJSL.IndexPage
import de.thm.icampus.joomdd.ejsl.eJSL.Component
import de.thm.icampus.joomdd.ejsl.eJSL.DetailsPage
import de.thm.icampus.joomdd.ejsl.generator.ps.JoomlaUtil.Slug
import de.thm.icampus.joomdd.ejsl.eJSL.Attribute
import de.thm.icampus.joomdd.ejsl.eJSL.Entity
import org.eclipse.emf.common.util.EList
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedPage.ExtendedDynamicPage
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedExtension.ExtendedComponent
import de.thm.icampus.joomdd.ejsl.generator.pi.ExtendedEntity.ExtendedAttribute

class IndexPageTemplateSiteHelper {
	
	 ExtendedDynamicPage indexpage
	private ExtendedComponent  com
	private String sec
	private DetailsPage details
	
	new(ExtendedDynamicPage dp, ExtendedComponent cp, String section){
		
		indexpage = dp
		com = cp
		sec = section
		details = Slug.getPageForDetails(indexpage,com)
		
	}
	
	public def CharSequence generateSiteViewDisplay() '''
	/**
	* Display the view
	*/
	    public function display($tpl = null) {
			
	        $user = JFactory::getUser();
	
	         $app = JFactory::getApplication();
	         $this->params = $app->getParams('«Slug.nameExtensionBind("com", com.name).toLowerCase»');
	                     $state = $this->params->get('state');
	                     if(!empty($state))
	                         $this->getModel()->setState('filter.state', $state);
	         
	                     $search = $this->params->get('search');
	                     if(!empty($search))
	                         $this->getModel()->setState('filter.search', $search);
	         
	                     $created_by = $this->params->get('created_by');
	                     if(!empty($created_by))
	                         $this->getModel()->setState('filter.search',$created_by);
	         
	                     $ordering = $this->params->get('ordering');
	                     if(!empty($ordering))
	                         $this->getModel()->setState('list.ordering',$ordering);
	         
	                     $direction = $this->params->get('direction');
	                     if(!empty($direction))
	                         $this->getModel()->setState('list.direction', $direction);
	         
	                     $start = $this->params->get('start');
	                     if(!empty($start))
	                         $this->getModel()->setState('list.start', $start);
	         
	                     $limit = $this->params->get('limit');
	                     if(!empty($limit))
	                         $this->getModel()->setState('list.limit', $limit);
	                   «FOR ExtendedAttribute attr: indexpage.extendedTableColumnList»
	                   $«attr.name» = $this->params->get('«attr.name»');
   	                     if(!empty($«attr.name»))
   	                         $this->getModel()->setState('filter.«attr.name»', $«attr.name»);
	                   «ENDFOR»

        	 $this->state = $this->get('State');
        	 
       		 $this->items = $this->get('Items');
        	 $this->pagination = $this->get('Pagination');
        	 $this->filterForm    = $this->get('FilterForm');
        	 $this->activeFilters = $this->get('ActiveFilters');
        
	        $this->params = $app->getParams('«Slug.nameExtensionBind("com", com.name).toLowerCase»');
	        
	        // Check for errors.
	        if (count($errors = $this->get('Errors'))) {
	            throw new Exception(implode("\n", $errors));
	        }
	
	        $this->_prepareDocument();
	
	        parent::display($tpl);
	}

	'''
	
	public def CharSequence generateSiteViewprepareDocument() '''
	 /**
	 * Prepares the document
	 */
	    protected function _prepareDocument() {
	        $app = JFactory::getApplication();
	        $menus = $app->getMenu();
	        $title = null;
	 
	        // Because the application sets a default page title,
	        // we need to get it from the menu item itself
	        $menu = $menus->getActive();
	        if ($menu) {
	            $this->params->def('page_heading', $this->params->get('page_title', $menu->title));
	        } else {
	            $this->params->def('page_heading', JText::_('«Slug.nameExtensionBind("com", com.name).toUpperCase»_DEFAULT_PAGE_TITLE'));
	        }
	        $title = $this->params->get('page_title', '');
	        if (empty($title)) {
	            $title = $app->getCfg('sitename');
	        } elseif ($app->getCfg('sitename_pagetitles', 0) == 1) {
	            $title = JText::sprintf('JPAGETITLE', $app->getCfg('sitename'), $title);
	        } elseif ($app->getCfg('sitename_pagetitles', 0) == 2) {
	            $title = JText::sprintf('JPAGETITLE', $title, $app->getCfg('sitename'));
	        }
	        $this->document->setTitle($title);
	 
	        if ($this->params->get('menu-meta_description')) {
	            $this->document->setDescription($this->params->get('menu-meta_description'));
	        }
	 
	        if ($this->params->get('menu-meta_keywords')) {
	            $this->document->setMetadata('keywords', $this->params->get('menu-meta_keywords'));
	        }
	 
	        if ($this->params->get('robots')) {
	            $this->document->setMetadata('robots', $this->params->get('robots'));
	        }
	    }
	'''
	
	public def CharSequence genViewTemplateInit()'''
	
		JHtml::addIncludePath(JPATH_COMPONENT . '/helpers/html');
		JHtml::_('bootstrap.tooltip');
		JHtml::_('behavior.multiselect');
		JHtml::_('formbehavior.chosen', 'select');
		
		$user = JFactory::getUser();
		$userId = $user->get('id');
		$listOrder = $this->state->get('list.ordering');
		$listDirn = $this->state->get('list.direction');
		$canCreate = $user->authorise('core.create', '«Slug.nameExtensionBind("com",com.name).toLowerCase»');
		$canEdit = $user->authorise('core.edit', '«Slug.nameExtensionBind("com",com.name).toLowerCase»');
		$canCheckin = $user->authorise('core.manage', '«Slug.nameExtensionBind("com",com.name).toLowerCase»');
		$canChange = $user->authorise('core.edit.state', '«Slug.nameExtensionBind("com",com.name).toLowerCase»');
		$canDelete = $user->authorise('core.delete', '«Slug.nameExtensionBind("com",com.name).toLowerCase»');
	?>
	
	'''
	
	public def CharSequence genViewTemplateHead()'''
	<form action="<?php echo JRoute::_('index.php?option=«Slug.nameExtensionBind("com",com.name).toLowerCase»&view=«indexpage.name.toLowerCase»'); ?>" method="post" name="adminForm" id="adminForm">
    <table class="table table-striped">
        <thead >
            <tr >
                <?php if (isset($this->items[0]->state)): ?>
        <th width="1%" class="nowrap center">
            <?php echo JHtml::_('grid.sort', 'JSTATUS', 'a.state', $listDirn, $listOrder); ?>
        </th>
    <?php endif; ?>
    «FOR Attribute attr: indexpage.tablecolumns»
    	<th class='left'>
			<?php echo JHtml::_('grid.sort',  '«Slug.nameExtensionBind("com", com.name).toUpperCase»_FORM_LBL_« (attr.eContainer as Entity).name.toUpperCase»_«attr.name.toUpperCase»', 'a.«attr.name.toLowerCase»', $listDirn, $listOrder); ?>
		</th>
    «ENDFOR»
    <?php if (isset($this->items[0]->id)): ?>
        <th width="1%" class="nowrap center hidden-phone">
            <?php echo JHtml::_('grid.sort', 'JGRID_HEADING_ID', 'a.id', $listDirn, $listOrder); ?>
        </th>
    <?php endif; ?>

		<?php if ($canEdit || $canDelete): ?>
		<th class="center">
	<?php echo JText::_('«Slug.nameExtensionBind("com", com.name).toUpperCase»_«indexpage.name.toUpperCase»_ACTIONS'); ?>
	</th>
	<?php endif; ?>

    </tr>
    </thead>
     
	«genViewTemplateBody()»
	 </tbody>
	 <tfoot>
	     <tr>
	          <td colspan="<?php echo $this->pagination->pagesStop + 5 ;?>">
	             <?php echo $this->pagination->getListFooter(); ?>
	         </td>
	     </tr>
	     </tfoot>
    </table>

    <?php if ($canCreate): ?>
        <a href="<?php echo JRoute::_('index.php?option=«Slug.nameExtensionBind("com", com.name).toLowerCase»&task=«details.name.toLowerCase»edit.edit&id=0', false, 2); ?>"
           class="btn btn-success btn-small"><i
                class="icon-plus"></i> <?php echo JText::_('«Slug.nameExtensionBind("com", com.name).toUpperCase»_ADD_ITEM'); ?></a>
    <?php endif; ?>

    <input type="hidden" name="task" value=""/>
    <input type="hidden" name="boxchecked" value="0"/>
    <input type="hidden" name="filter_order" value="<?php echo $listOrder; ?>"/>
    <input type="hidden" name="filter_order_Dir" value="<?php echo $listDirn; ?>"/>
    <?php echo JHtml::_('form.token'); ?>
</form>
<script type="text/javascript">

    jQuery(document).ready(function () {
        jQuery('.delete-button').click(deleteItem);
    });

    function deleteItem() {
        var item_id = jQuery(this).attr('data-item-id');
        if (confirm("<?php echo JText::_('«Slug.nameExtensionBind("com", com.name).toUpperCase»_DELETE_MESSAGE'); ?>")) {
            window.location.href = '<?php echo JRoute::_('index.php?option=«Slug.nameExtensionBind("com", com.name).toLowerCase»&task=«details.name.toLowerCase»edit.remove&id=', false, 2) ?>' + item_id;
        }
    }
</script>    
	'''
	public def CharSequence genViewTemplateBody()'''
	 <tbody>
    <?php foreach ($this->items as $i => $item) : ?>
        <?php $canEdit = $user->authorise('core.edit', '«Slug.nameExtensionBind("com", com.name).toLowerCase»'); ?>

				<?php if (!$canEdit && $user->authorise('core.edit.own', '«Slug.nameExtensionBind("com", com.name).toLowerCase»')): ?>
			<?php $canEdit = JFactory::getUser()->id == $item->created_by; ?>
		<?php endif; ?>
		 <tr class="row<?php echo $i % 2; ?>">

            <?php if (isset($this->items[0]->state)): ?>
                <?php $class = ($canEdit || $canChange) ? 'active' : 'disabled'; ?>
                <td class="center">
                    <a class="btn btn-micro <?php echo $class; ?>"
                       href="<?php echo ($canEdit || $canChange) ? JRoute::_('index.php?option=«Slug.nameExtensionBind("com", com.name).toLowerCase»&task=«details.name.toLowerCase»edit.publish&id=' . $item->id . '&state=' . (($item->state + 1) % 2), false, 2) : '#'; ?>">
                        <?php if ($item->state == 1): ?>
                            <i class="icon-publish"></i>
                        <?php else: ?>
                            <i class="icon-unpublish"></i>
                        <?php endif; ?>
                    </a>
                </td>
            <?php endif; ?>
         
           «genSiteModelAttributeReference(indexpage.extendedTableColumnList, indexpage,com)»

            <?php if (isset($this->items[0]->id)): ?>
                <td class="center hidden-phone">
                    <?php echo (int)$item->id; ?>
                </td>
            <?php endif; ?>

            				<?php if ($canEdit || $canDelete): ?>
					<td class="center">
						<?php if ($canEdit): ?>
							<a href="<?php echo JRoute::_('index.php?option=«Slug.nameExtensionBind("com", com.name).toLowerCase»&task=«details.name.toLowerCase»edit.edit&id=' . $item->id, false, 2); ?>" class="btn btn-mini" type="button"><i class="icon-edit" ></i></a>
						<?php endif; ?>
						<?php if ($canDelete): ?>
                            <a href="<?php echo JRoute::_('index.php?option=«Slug.nameExtensionBind("com", com.name).toLowerCase»&task=«details.name.toLowerCase»edit.remove&id=' . $item->id, false, 2); ?>" class="btn btn-mini delete-button" type="button"><i class="icon-trash" ></i></a>
                            <?php endif; ?>
					</td>
				<?php endif; ?>

        </tr>
    <?php endforeach; ?>
   
		
	'''
	public   def CharSequence genSiteModelAttributeReference(EList<ExtendedAttribute>column, ExtendedDynamicPage indexpage, Component com )'''
 	« FOR ExtendedAttribute attr : column»
	«IF Slug.isAttributeLinked(attr, indexpage)»
	
	<td>
		<a href="<?php echo JRoute::_(«Slug.linkOfAttribut(attr, indexpage, com.name, "$item->")»); ?>">
			<?php echo $this->escape($item->«attr.name.toLowerCase»); ?></a>
	«ELSE»
		<td>
		<?php echo $item->«attr.name.toLowerCase»; ?>
		</td>
	«ENDIF»
	«ENDFOR»
 '''
}