package de.thm.icampus.ejsl.generator

import de.thm.icampus.ejsl.eJSL.IndexPage
import de.thm.icampus.ejsl.eJSL.Component
import de.thm.icampus.ejsl.eJSL.Section
import de.thm.icampus.ejsl.eJSL.BackendSection
import de.thm.icampus.ejsl.eJSL.Entity
import java.util.List
import de.thm.icampus.ejsl.eJSL.Attribute

class IndexPageTemplate extends DynamicPageTemplate {

	private IndexPage ipage
	private Component com
	private Section sec

	new(IndexPage dp, Component cp, Section section) {

		ipage = dp
		com = cp
		sec = section
	}

	def CharSequence generateViewBackend(IndexPage page, Component component, Entity entity) '''
		<?php
		/**
		«generateFileDoc(page, component)»
		* @description «page.entities.get(0).name»View for «component.name»
		*/
		defined('_JEXEC') or die('Restricted access');
		jimport('joomla.application.component.view');
		jimport('joomla.filesystem.path');
		/**
		* «component.name»View class for component com_«Slug.slugify(component.name)»
		*
		* @category Joomla.Component. Admin
		* @package com_«Slug.slugify(component.name)»."admin"
		* @since Class available since Release x.x.x
		*/
		class «component.name.toFirstUpper»View«page.name.toFirstUpper» extends JViewLegacy
		{
		 	protected $items;
		
			protected $pagination;
		
			protected $state;
		/**
		* @var JObject A cache for the available actions.
		* @generated
		*
		*/
		protected static $actions;
		/**
		* Method to get display
		*
		* @param Object $tpl template
		*
		* @return void
		* @generated
		*/
		public function display($tpl = null)
		{
			$this->state		= $this->get('State');
			$this->items		= $this->get('Items');
			$this->pagination	= $this->get('Pagination');
			
			«page.name.toFirstUpper»Helper::addSubmenu('«page.name»');
			
			// Check for errors.
			if (count($errors = $this->get('Errors')))
			{
				JError::raiseError(500, implode("\n", $errors));
				return false;
			}
		
			$this->addToolbar();
			$this->sidebar = JHtmlSidebar::render();
			parent::display($tpl);
		}
		
		 protected function addToolbar() {
		      require_once JPATH_COMPONENT . '/helpers/«page.name».php';
		
		      $state = $this->get('State');
		      $canDo = MddHelper::getActions($state->get('filter.category_id'));
		
		      JToolBarHelper::title(JText::_('«page.name»'));
		
		      //Check if the form exists before showing the add/edit buttons
		      $formPath = JPATH_COMPONENT_ADMINISTRATOR . '/views/«page.name»';
		      if (file_exists($formPath)) {
		
		    if ($canDo->get('core.create')) {
		        JToolBarHelper::addNew('«page.name».add', 'JTOOLBAR_NEW');
		    }
		
		          if ($canDo->get('core.edit') && isset($this->items[0])) {
		    JToolBarHelper::editList('«page.name».edit', 'JTOOLBAR_EDIT');
		          }
		      }
		
		      if ($canDo->get('core.edit.state')) {
		
		    if (isset($this->items[0]->state)) {
		        JToolBarHelper::divider();
		        JToolBarHelper::custom('«page.name»s.publish', 'publish.png', 'publish_f2.png', 'JTOOLBAR_PUBLISH', true);
		        JToolBarHelper::custom('«page.name»s.unpublish', 'unpublish.png', 'unpublish_f2.png', 'JTOOLBAR_UNPUBLISH', true);
		    } else if (isset($this->items[0])) {
		        //If this component does not use state then show a direct delete button as we can not trash
		        JToolBarHelper::deleteList('', '«page.name»s.delete', 'JTOOLBAR_DELETE');
		    }
		
		          if (isset($this->items[0]->state)) {
		    JToolBarHelper::divider();
		    JToolBarHelper::archiveList('«page.name»s.archive', 'JTOOLBAR_ARCHIVE');
		          }
		          if (isset($this->items[0]->checked_out)) {
		              JToolBarHelper::custom('«page.name»s.checkin', 'checkin.png', 'checkin_f2.png', 'JTOOLBAR_CHECKIN', true);
		          }
		      }
		
		      //Show trash and delete for components that uses the state field
		      if (isset($this->items[0]->state)) {
		    if ($state->get('filter.state') == -2 && $canDo->get('core.delete')) {
		        JToolBarHelper::deleteList('', '«page.name»s.delete', 'JTOOLBAR_EMPTY_TRASH');
		        JToolBarHelper::divider();
		    } else if ($canDo->get('core.edit.state')) {
		        JToolBarHelper::trash('«page.name»s.trash', 'JTOOLBAR_TRASH');
		        JToolBarHelper::divider();
		    }
		      }
		
		      if ($canDo->get('core.admin')) {
		    JToolBarHelper::preferences('com_«component.name»');
		      }
		
		      //Set sidebar action - New in 3.0
		      JHtmlSidebar::setAction('index.php?option=com_mdd&view=«page.name»s');
		
		      $this->extra_sidebar = '';
		      
		JHtmlSidebar::addFilter(
		
			JText::_('JOPTION_SELECT_PUBLISHED'),
		
			'filter_published',
		
			JHtml::_('select.options', JHtml::_('jgrid.publishedOptions'), "value", "text", $this->state->get('filter.state'), true)
		
		);
		
		//Filter for the field user
		$this->extra_sidebar .= '<small><label for="filter_user">User</label></small>';
		$this->extra_sidebar .= JHtmlList::users('filter_user', $this->state->get('filter.user'), 1, 'onchange="this.form.submit();"');
		  }
		
		protected function getSortFields()
		{
			return array(
			'a.id' => JText::_('JGRID_HEADING_ID'),
			«readFields(entity, component)»
			);
		}
			
			}
	'''

	def CharSequence readFields(Entity entities, Component com) {
		var StringBuffer buff = new StringBuffer
		var int count = 0;
		for (Attribute e : entities.attributes) {
			if (count >= 5)
				buff.append('a.' + e.name + ''' => JText::_('COM_«com.name.toUpperCase»_«e.name.toUpperCase»'),''')
		}
		return buff.toString
	}

	def CharSequence generateDefaultView(IndexPage page, Component component, Entity entity) '''
	<?php
/**
«generateFileDoc(page, component)»
 */

// no direct access
defined('_JEXEC') or die;

JHtml::addIncludePath(JPATH_COMPONENT.'/helpers/html');
JHtml::_('bootstrap.tooltip');
JHtml::_('behavior.multiselect');
JHtml::_('formbehavior.chosen', 'select');

// Import CSS
$document = JFactory::getDocument();
$document->addStyleSheet('components/com_«component.name.toLowerCase»/assets/css/«component.name.toLowerCase».css');

$user	= JFactory::getUser();
$userId	= $user->get('id');
$listOrder	= $this->state->get('list.ordering');
$listDirn	= $this->state->get('list.direction');
$canOrder	= $user->authorise('core.edit.state', 'com_«component.name.toLowerCase»');
$saveOrder	= $listOrder == 'a.ordering';
if ($saveOrder)
{
	$saveOrderingUrl = 'index.php?option=com_«component.name.toLowerCase»&task=buchs.saveOrderAjax&tmpl=component';
	JHtml::_('sortablelist.sortable', '«page.name.toLowerCase»List', 'adminForm', strtolower($listDirn), $saveOrderingUrl);
}
$sortFields = $this->getSortFields();
?>
<script type="text/javascript">
	Joomla.orderTable = function() {
		table = document.getElementById("sortTable");
		direction = document.getElementById("directionTable");
		order = table.options[table.selectedIndex].value;
		if (order != '<?php echo $listOrder; ?>') {
			dirn = 'asc';
		} else {
			dirn = direction.options[direction.selectedIndex].value;
		}
		Joomla.tableOrdering(order, dirn, '');
	}
</script>

<?php
//Joomla Component Creator code to allow adding non select list filters
if (!empty($this->extra_sidebar)) {
    $this->sidebar .= $this->extra_sidebar;
}
?>

<form action="<?php echo JRoute::_('index.php?option=com_mdd&view=«page.name.toLowerCase»s'); ?>" method="post" name="adminForm" id="adminForm">
<?php if(!empty($this->sidebar)): ?>
	<div id="j-sidebar-container" class="span2">
		<?php echo $this->sidebar; ?>
	</div>
	<div id="j-main-container" class="span10">
<?php else : ?>
	<div id="j-main-container">
<?php endif;?>
    
		<div id="filter-bar" class="btn-toolbar">
			<div class="filter-search btn-group pull-left">
				<label for="filter_search" class="element-invisible"><?php echo JText::_('JSEARCH_FILTER');?></label>
				<input type="text" name="filter_search" id="filter_search" placeholder="<?php echo JText::_('JSEARCH_FILTER'); ?>" value="<?php echo $this->escape($this->state->get('filter.search')); ?>" title="<?php echo JText::_('JSEARCH_FILTER'); ?>" />
			</div>
			<div class="btn-group pull-left">
				<button class="btn hasTooltip" type="submit" title="<?php echo JText::_('JSEARCH_FILTER_SUBMIT'); ?>"><i class="icon-search"></i></button>
				<button class="btn hasTooltip" type="button" title="<?php echo JText::_('JSEARCH_FILTER_CLEAR'); ?>" onclick="document.id('filter_search').value='';this.form.submit();"><i class="icon-remove"></i></button>
			</div>
			<div class="btn-group pull-right hidden-phone">
				<label for="limit" class="element-invisible"><?php echo JText::_('JFIELD_PLG_SEARCH_SEARCHLIMIT_DESC');?></label>
				<?php echo $this->pagination->getLimitBox(); ?>
			</div>
		<!--	<div class="btn-group pull-right hidden-phone">
				<label for="directionTable" class="element-invisible"><?php echo JText::_('JFIELD_ORDERING_DESC');?></label>
				<select name="directionTable" id="directionTable" class="input-medium" onchange="Joomla.orderTable()">
					<option value=""><?php echo JText::_('JFIELD_ORDERING_DESC');?></option>
					<option value="asc" <?php if ($listDirn == 'asc') echo 'selected="selected"'; ?>><?php echo JText::_('JGLOBAL_ORDER_ASCENDING');?></option>
					<option value="desc" <?php if ($listDirn == 'desc') echo 'selected="selected"'; ?>><?php echo JText::_('JGLOBAL_ORDER_DESCENDING');?></option>
				</select>
			</div> 
			-->
			<div class="btn-group pull-right">
				<label for="sortTable" class="element-invisible"><?php echo JText::_('JGLOBAL_SORT_BY');?></label>
				<select name="sortTable" id="sortTable" class="input-medium" onchange="Joomla.orderTable()">
					<option value=""><?php echo JText::_('JGLOBAL_SORT_BY');?></option>
					<?php echo JHtml::_('select.options', $sortFields, 'value', 'text', $listOrder);?>
				</select>
			</div>
		</div>        
		<div class="clearfix"> </div>
		<table class="table table-striped" id="«page.name.toLowerCase»List">
			<thead>
				<tr>
                <?php if (isset($this->items[0]->ordering)): ?>
					<th width="1%" class="nowrap center hidden-phone">
						<?php echo JHtml::_('grid.sort', '<i class="icon-menu-2"></i>', 'a.ordering', $listDirn, $listOrder, null, 'asc', 'JGRID_HEADING_ORDERING'); ?>
					</th>
                <?php endif; ?>
					<th width="1%" class="hidden-phone">
						<input type="checkbox" name="checkall-toggle" value="" title="<?php echo JText::_('JGLOBAL_CHECK_ALL'); ?>" onclick="Joomla.checkAll(this)" />
					</th>
                <?php if (isset($this->items[0]->state)): ?>
					<th width="1%" class="nowrap center">
						<?php echo JHtml::_('grid.sort', 'JSTATUS', 'a.state', $listDirn, $listOrder); ?>
					</th>
                <?php endif; ?>
				<th class='left'>
				<?php echo JHtml::_('grid.sort',  'COM_MDD_BUCHS_TITLE', 'a.«entity.attributes.get(0).name»', $listDirn, $listOrder); ?>
				</th>
				<th class='left'>
				<?php echo JHtml::_('grid.sort',  'COM_MDD_BUCHS_USER', 'a.«entity.attributes.get(1).name»', $listDirn, $listOrder); ?>
				</th>
				<th class='left'>
				<?php echo JHtml::_('grid.sort',  'COM_MDD_BUCHS_ALIAS', 'a.alias', $listDirn, $listOrder); ?>
				</th>                    
                <?php if (isset($this->items[0]->id)): ?>
					<th width="1%" class="nowrap center hidden-phone">
						<?php echo JHtml::_('grid.sort', 'JGRID_HEADING_ID', 'a.id', $listDirn, $listOrder); ?>
					</th>
                <?php endif; ?>
				</tr>
			</thead>
			<tfoot>
                <?php 
                if(isset($this->items[0])){
                    $colspan = count(get_object_vars($this->items[0]));
                }
                else{
                    $colspan = 10;
                }
            ?>
			<tr>
				<td colspan="<?php echo $colspan ?>">
					<?php echo $this->pagination->getListFooter(); ?>
				</td>
			</tr>
			</tfoot>
			<tbody>
			<?php foreach ($this->items as $i => $item) :
				$ordering   = ($listOrder == 'a.ordering');
                $canCreate	= $user->authorise('core.create',		'com_mdd');
                $canEdit	= $user->authorise('core.edit',			'com_mdd');
                $canCheckin	= $user->authorise('core.manage',		'com_mdd');
                $canChange	= $user->authorise('core.edit.state',	'com_mdd');
				?>
				<tr class="row<?php echo $i % 2; ?>">
                    
                <?php if (isset($this->items[0]->ordering)): ?>
					<td class="order nowrap center hidden-phone">
					<?php if ($canChange) :
						$disableClassName = '';
						$disabledLabel	  = '';
						if (!$saveOrder) :
							$disabledLabel    = JText::_('JORDERINGDISABLED');
							$disableClassName = 'inactive tip-top';
						endif; ?>
						<span class="sortable-handler hasTooltip <?php echo $disableClassName?>" title="<?php echo $disabledLabel?>">
							<i class="icon-menu"></i>
						</span>
						<input type="text" style="display:none" name="order[]" size="5" value="<?php echo $item->ordering;?>" class="width-20 text-area-order " />
					<?php else : ?>
						<span class="sortable-handler inactive" >
							<i class="icon-menu"></i>
						</span>
					<?php endif; ?>
					</td>
                <?php endif; ?>
					<td class="center hidden-phone">
						<?php echo JHtml::_('grid.id', $i, $item->id); ?>
					</td>
                <?php if (isset($this->items[0]->state)): ?>
					<td class="center">
						<?php echo JHtml::_('jgrid.published', $item->state, $i, 'buchs.', $canChange, 'cb'); ?>
					</td>
                <?php endif; ?>
                    
				<td>
				<?php if (isset($item->checked_out) && $item->checked_out) : ?>
					<?php echo JHtml::_('jgrid.checkedout', $i, $item->editor, $item->checked_out_time, 'buchs.', $canCheckin); ?>
				<?php endif; ?>
				<?php if ($canEdit) : ?>
					<a href="<?php echo JRoute::_('index.php?option=com_mdd&task=buch.edit&id='.(int) $item->id); ?>">
					<?php echo $this->escape($item->title); ?></a>
				<?php else : ?>
					<?php echo $this->escape($item->title); ?>
				<?php endif; ?>
				</td>
				<td>

					<?php echo $item->user; ?>
				</td>
				<td>

					<?php echo $item->alias; ?>
				</td>


                <?php if (isset($this->items[0]->id)): ?>
					<td class="center hidden-phone">
						<?php echo (int) $item->id; ?>
					</td>
                <?php endif; ?>
				</tr>
				<?php endforeach; ?>
			</tbody>
		</table>

		<input type="hidden" name="task" value="" />
		<input type="hidden" name="boxchecked" value="0" />
		<input type="hidden" name="filter_order" value="<?php echo $listOrder; ?>" />
		<input type="hidden" name="filter_order_Dir" value="<?php echo $listDirn; ?>" />
		<?php echo JHtml::_('form.token'); ?>
	</div>
</form>        
	
	'''
	

}
