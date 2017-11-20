<?php
/**
 * Created by PhpStorm.
 * User: samuel
 * Date: 11/14/17
 * Time: 6:44 PM
 */

namespace AppBundle\Entity\Types;

use Fresh\DoctrineEnumBundle\DBAL\Types\AbstractEnumType;

class FormType extends AbstractEnumType
{
    const INTERNAL_INCIDENT = 'internal_incident';
    const BOWEL_CHECKLIST = 'bowel_checklist';
    const COMMUNITY_SHEET = 'community_sheet';
    const GOAL_TRACKING = 'goal_tracking';

    protected static $choices = [
        self::INTERNAL_INCIDENT => 'Internal Incidents',
        self::BOWEL_CHECKLIST => 'Bowel Checklist',
        self::COMMUNITY_SHEET => 'Community Sheet',
        self::GOAL_TRACKING => 'Goal Tracking'
    ];

    static function getTwigTemplate($type)
    {
        return "notes/forms/$type.html.twig";
    }
}